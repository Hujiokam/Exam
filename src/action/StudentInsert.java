package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("/kadai/studentinsert.jsp").forward(request, response);
    }

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

        String no = request.getParameter("no");
        String name = request.getParameter("name");
        String entYearStr = request.getParameter("ent_year");
        String classNum = request.getParameter("class_num");
        boolean isAttend = "1".equals(request.getParameter("is_attend"));
        String schoolCd = teacher.getSchool_cd();

        String message = null;

        // 入力保持用にリクエストへセット
        request.setAttribute("param.no", no);
        request.setAttribute("param.name", name);
        request.setAttribute("param.ent_year", entYearStr);
        request.setAttribute("param.class_num", classNum);

        // 入学年度の数値変換
        int entYear = 0;
        try {
            entYear = Integer.parseInt(entYearStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "入学年度が不正です。");
            request.getRequestDispatcher("/kadai/studentinsert.jsp").forward(request, response);
            return;
        }

        try {
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/java-kadai");
            Connection con = ds.getConnection();

            // ✅ 学生番号の重複チェック
            PreparedStatement checkSt = con.prepareStatement(
                "SELECT COUNT(*) FROM student WHERE no = ? AND school_cd = ?"
            );
            checkSt.setString(1, no);
            checkSt.setString(2, schoolCd);
            ResultSet rs = checkSt.executeQuery();

            boolean isDuplicate = false;
            if (rs.next()) {
                isDuplicate = rs.getInt(1) > 0;
            }

            rs.close();
            checkSt.close();

            if (isDuplicate) {
                // ❌ 学生番号が重複していたらフォームに戻す
                request.setAttribute("errorMessage", "この学生番号は既に登録されています。");

                // 年リストの再生成
                int currentYear = java.time.Year.now().getValue();
                java.util.List<Integer> entYearList = new java.util.ArrayList<>();
                for (int i = currentYear - 10; i <= currentYear + 10; i++) {
                    entYearList.add(i);
                }

                // クラス一覧再取得
                PreparedStatement clsSt = con.prepareStatement(
                    "SELECT class_num FROM class_num WHERE school_cd = ? ORDER BY class_num"
                );
                clsSt.setString(1, schoolCd);
                ResultSet clsRs = clsSt.executeQuery();
                java.util.List<String> classNumList = new java.util.ArrayList<>();
                while (clsRs.next()) {
                    classNumList.add(clsRs.getString("class_num"));
                }
                clsRs.close();
                clsSt.close();

                request.setAttribute("ent_year_list", entYearList);
                request.setAttribute("class_num_list", classNumList);

                con.close();

                // 入力フォームに戻す
                request.getRequestDispatcher("/kadai/studentinsert.jsp").forward(request, response);
                return;
            }

            // ✅ 重複なし → 登録実行
            PreparedStatement st = con.prepareStatement(
                "INSERT INTO STUDENT (no, name, ent_year, class_num, is_attend, school_cd) " +
                "VALUES (?, ?, ?, ?, ?, ?)"
            );
            st.setString(1, no);
            st.setString(2, name);
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
