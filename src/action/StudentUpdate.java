package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/action/StudentUpdate.action")
public class StudentUpdate extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    // フォームから受け取るパラメータ
    String no = request.getParameter("no"); // 学生番号（PK）
    String name = request.getParameter("name");
    String classNum = request.getParameter("class_num");
    boolean isAttend = request.getParameter("is_attend") != null;

    String message;

    try {
      // データベース接続
      InitialContext ic = new InitialContext();
      DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/java-kadai");
      Connection con = ds.getConnection();

      // 更新SQLの実行
      PreparedStatement st = con.prepareStatement(
        "UPDATE student SET name = ?, class_num = ?, is_attend = ? WHERE no = ?"
      );
      st.setString(1, name);
      st.setString(2, classNum);
      st.setBoolean(3, isAttend);
      st.setString(4, no);

      int rows = st.executeUpdate();

      st.close();
      con.close();

      message = (rows > 0) ? "変更が完了しました。" : "変更に失敗しました。";

    } catch (Exception e) {
      e.printStackTrace();
      message = "エラーが発生しました。<br>" + e.getMessage();
    }

    request.setAttribute("message", message);
    request.getRequestDispatcher("/kadai/studentupdateresult.jsp").forward(request, response);
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // GETリクエストが来たときは POST に転送
    doPost(request, response);
  }
}
