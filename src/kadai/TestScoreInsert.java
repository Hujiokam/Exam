package kadai;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DAO;

@WebServlet("/kadai/TestScoreInsert.action")
public class TestScoreInsert extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    HttpSession session = request.getSession();
    String entYear = request.getParameter("entYear");
    String classNum = request.getParameter("classNum");
    String subjectName = request.getParameter("subject");
    String examNo = request.getParameter("examNo");

    // ログイン済みの教師情報から学校コードを取得
    String schoolCd = (String) session.getAttribute("schoolCd");
    if (schoolCd == null && session.getAttribute("teacher") != null) {
      schoolCd = ((bean.Teacher) session.getAttribute("teacher")).getSchool_cd();
    }

    String subjectCd = null;
    String message = "登録に失敗しました。";

    try {
      DAO dao = new DAO();
      try (Connection con = dao.getConnection()) {

        // 科目名 → CD に変換
        try (PreparedStatement st = con.prepareStatement(
            "SELECT cd FROM SUBJECT WHERE school_cd = ? AND name = ?")) {
          st.setString(1, schoolCd);
          st.setString(2, subjectName);
          try (ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
              subjectCd = rs.getString("cd");
            }
          }
        }

        if (subjectCd == null) {
          message = "科目コードの取得に失敗しました。";
        } else {
          boolean hasSuccess = false;

          // 全パラメータを確認してスコアを登録
          for (String param : request.getParameterMap().keySet()) {
            if (param.startsWith("score_")) {
              String studentNo = param.substring(6);
              String scoreStr = request.getParameter(param);

              if (scoreStr != null && !scoreStr.isEmpty()) {
                try (PreparedStatement st = con.prepareStatement(
                    "MERGE INTO TEST " +
                    "KEY (student_no, subject_cd, school_cd, no) " +
                    "VALUES (?, ?, ?, ?, ?, ?)")) {

                  st.setString(1, studentNo);
                  st.setString(2, subjectCd);
                  st.setString(3, schoolCd);
                  st.setString(4, examNo);
                  st.setInt(5, Integer.parseInt(scoreStr));
                  st.setString(6, classNum);

                  int result = st.executeUpdate();
                  if (result > 0) hasSuccess = true;
                }
              }
            }
          }

          message = hasSuccess ? "登録が完了しました。" : "登録するデータがありませんでした。";
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      message = "登録に失敗しました。<br>" + e.getMessage();
    }

    // メッセージを渡して画面遷移
    request.setAttribute("message", message);
    request.getRequestDispatcher("/kadai/testregist2.jsp").forward(request, response);
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }
}