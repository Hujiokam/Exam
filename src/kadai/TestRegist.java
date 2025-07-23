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

import bean.Student;
import bean.Teacher;
import dao.DAO;

@WebServlet("/kadai/TestRegist")
public class TestRegist extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");
    HttpSession session = request.getSession();
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    if (teacher == null) {
      response.sendRedirect("../login.jsp");
      return;
    }

    String schoolCd = teacher.getSchool_cd();
    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String subject = request.getParameter("subject");
    String examNo = request.getParameter("examNo");

    List<String> entYears = new ArrayList<>();
    List<String> classNums = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    List<String> examNos = new ArrayList<>();
    List<Student> students = new ArrayList<>();

    try {
      DAO dao = new DAO();
      try (Connection con = dao.getConnection()) {

        // 入学年度
        try (PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT ent_year FROM STUDENT WHERE school_cd = ? ORDER BY ent_year")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              entYears.add(rs.getString("ent_year"));
            }
          }
        }

        // クラス
        try (PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT class_num FROM STUDENT WHERE school_cd = ? ORDER BY class_num")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              classNums.add(rs.getString("class_num"));
            }
          }
        }

        // 科目
        try (PreparedStatement st = con.prepareStatement(
            "SELECT DISTINCT name FROM SUBJECT WHERE school_cd = ? ORDER BY name")) {
          st.setString(1, schoolCd);
          try (ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
              subjects.add(rs.getString("name"));
            }
          }
        }

        // 回数
        for (int i = 1; i <= 5; i++) {
          examNos.add(String.valueOf(i));
        }

        // 検索条件に一致する学生
        if (entYear != null && !entYear.isEmpty() &&
            classNum != null && !classNum.isEmpty()) {

          try (PreparedStatement st = con.prepareStatement(
              "SELECT no, name, ent_year, class_num FROM STUDENT WHERE school_cd = ? AND ent_year = ? AND class_num = ? ORDER BY no")) {
            st.setString(1, schoolCd);
            st.setString(2, entYear);
            st.setString(3, classNum);
            try (ResultSet rs = st.executeQuery()) {
              while (rs.next()) {
                Student s = new Student();
                s.setNo(rs.getString("no"));
                s.setName(rs.getString("name"));
                s.setEntYear(rs.getString("ent_year"));
                s.setClassNum(rs.getString("class_num"));
                students.add(s);
              }
            }
          }
        }
      }
    } catch (Exception e) {
      throw new ServletException(e);
    }

    request.setAttribute("entYears", entYears);
    request.setAttribute("classNums", classNums);
    request.setAttribute("subjects", subjects);
    request.setAttribute("examNos", examNos);

    request.setAttribute("entYear", entYear);
    request.setAttribute("classNum", classNum);
    request.setAttribute("subject", subject);
    request.setAttribute("examNo", examNo);
    request.setAttribute("students", students);

    request.getRequestDispatcher("/kadai/testregist.jsp").forward(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}