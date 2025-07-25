package kadai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ScoreView;
import bean.Teacher;
import dao.DAO;

@WebServlet("/kadai/TestList.action")
public class TestList extends HttpServlet {
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    String schoolCd = ((Teacher) session.getAttribute("teacher")).getSchool_cd();

    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String subject = request.getParameter("subject");
    String studentNo = request.getParameter("studentNo");

    List<ScoreView> scores = new ArrayList<>();
    List<String> entYears = new ArrayList<>();
    List<String> classNums = new ArrayList<>();
    List<String> subjects = new ArrayList<>();

    try {
      DAO dao = new DAO();
      try (Connection con = dao.getConnection()) {

        // 入学年度リスト
        try (PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ent_year FROM STUDENT WHERE school_cd = ? ORDER BY ent_year")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              entYears.add(rs.getString("ent_year"));
            }
          }
        }

        // クラスリスト
        try (PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT class_num FROM STUDENT WHERE school_cd = ? ORDER BY class_num")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              classNums.add(rs.getString("class_num"));
            }
          }
        }

        // 科目リスト
        try (PreparedStatement st = con.prepareStatement(
            "SELECT name FROM SUBJECT WHERE school_cd = ? ORDER BY name")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              subjects.add(rs.getString("name"));
            }
          }
        }

        // 科目コード取得
        String subjectCd = null;
        if (subject != null && !subject.isEmpty()) {
          try (PreparedStatement st = con.prepareStatement(
              "SELECT cd FROM SUBJECT WHERE school_cd = ? AND name = ?")) {
            st.setString(1, schoolCd);
            st.setString(2, subject);
            try (ResultSet rs = st.executeQuery()) {
              if (rs.next()) subjectCd = rs.getString("cd");
            }
          }
        }

        if (studentNo != null && !studentNo.isEmpty()) {
          // 学生番号で複数科目成績取得
          String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, " +
                       "sub.name AS subject_name, sub.cd AS subject_cd, " +
                       "MAX(CASE WHEN t.no = '1' THEN t.POINT END) AS score1, " +
                       "MAX(CASE WHEN t.no = '2' THEN t.POINT END) AS score2 " +
                       "FROM STUDENT s " +
                       "LEFT JOIN TEST t ON s.no = t.student_no AND t.school_cd = ? " +
                       "LEFT JOIN SUBJECT sub ON t.subject_cd = sub.cd AND sub.school_cd = ? " +
                       "WHERE s.school_cd = ? AND s.no = ? " +
                       "GROUP BY s.no, s.name, s.ent_year, s.class_num, sub.name, sub.cd";

          try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, schoolCd);
            st.setString(2, schoolCd);
            st.setString(3, schoolCd);
            st.setString(4, studentNo);

            try (ResultSet rs = st.executeQuery()) {
              while (rs.next()) {
                ScoreView sv = new ScoreView();
                sv.setStudentNo(rs.getString("no"));
                sv.setName(rs.getString("name"));
                sv.setEntYear(rs.getString("ent_year"));
                sv.setClassNum(rs.getString("class_num"));
                sv.setSubjectName(rs.getString("subject_name"));
                sv.setSubjectCd(rs.getString("subject_cd"));
                sv.setScore1(rs.getString("score1"));
                sv.setScore2(rs.getString("score2"));
                scores.add(sv);
              }
            }
          }
        } else if (entYear != null && classNum != null && subjectCd != null) {
          // 通常のクラス＋科目検索
          String sql = "SELECT s.no, s.name, s.ent_year, s.class_num, " +
                       "MAX(CASE WHEN t.no = '1' THEN t.POINT END) AS score1, " +
                       "MAX(CASE WHEN t.no = '2' THEN t.POINT END) AS score2 " +
                       "FROM STUDENT s LEFT JOIN TEST t " +
                       "ON s.no = t.student_no AND t.subject_cd = ? AND t.school_cd = ? " +
                       "WHERE s.school_cd = ? AND s.ent_year = ? AND s.class_num = ? " +
                       "GROUP BY s.no, s.name, s.ent_year, s.class_num " +
                       "ORDER BY s.no";

          try (PreparedStatement st = con.prepareStatement(sql)) {
            st.setString(1, subjectCd);
            st.setString(2, schoolCd);
            st.setString(3, schoolCd);
            st.setString(4, entYear);
            st.setString(5, classNum);

            try (ResultSet rs = st.executeQuery()) {
              while (rs.next()) {
                ScoreView sv = new ScoreView();
                sv.setStudentNo(rs.getString("no"));
                sv.setName(rs.getString("name"));
                sv.setEntYear(rs.getString("ent_year"));
                sv.setClassNum(rs.getString("class_num"));
                sv.setScore1(rs.getString("score1"));
                sv.setScore2(rs.getString("score2"));
                scores.add(sv);
              }
            }
          }
        }

      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    // 値をセット
    request.setAttribute("entYears", entYears);
    request.setAttribute("classNums", classNums);
    request.setAttribute("subjects", subjects);
    request.setAttribute("entYear", entYear);
    request.setAttribute("classNum", classNum);
    request.setAttribute("subject", subject);
    request.setAttribute("studentNo", studentNo);
    request.setAttribute("scores", scores);

    request.getRequestDispatcher("/kadai/testlist-form.jsp").forward(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }
}