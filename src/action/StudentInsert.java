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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import bean.Teacher;

@WebServlet("/action/studentinsert")
public class StudentInsert extends HttpServlet {

    // GETリクエストに対応（入力フォームを表示）
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // セッションチェック（ログインしていない場合はログイン画面にリダイレクト）
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // 入力フォームのJSPに転送
        request.getRequestDispatcher("/kadai/studentinsert.jsp").forward(request, response);
    }

    // POSTリクエストに対応（学生データの登録処理）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String No = request.getParameter("no");
        String Name = request.getParameter("name");
        int entYear = Integer.parseInt(request.getParameter("ent_year"));
        String classNum = request.getParameter("class_num");
        boolean isAttend = "1".equals(request.getParameter("is_attend"));
        String schoolCd = teacher.getSchool_cd(); // ログイン中の教員の学校コード

        String message;

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/java-kadai");
            Connection con = ds.getConnection();

            PreparedStatement st = con.prepareStatement(
                "INSERT INTO STUDENT (no, name, ent_year, class_num, is_attend, school_cd) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );
            st.setString(1, No);
            st.setString(2, Name);
            st.setInt(3, entYear);
            st.setString(4, classNum);
            st.setBoolean(5, isAttend);
            st.setString(6, schoolCd);

            int rows = st.executeUpdate();
            message = (rows > 0) ? "登録が完了しました。" : "登録に失敗しました。";

            st.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            message = "登録中にエラーが発生しました。<br>" + e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/kadai/studentinsertresult.jsp").forward(request, response);
    }
}
