package subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectDelete.action")
public class SubjectDelete extends HttpServlet {

    // 削除確認画面表示 (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String subjectCd = request.getParameter("subject_cd");
        String subjectName = request.getParameter("subject_name");

        // パラメータチェック
        if (subjectCd == null || subjectName == null) {
            response.sendRedirect(request.getContextPath() + "/subject/SubjectList.action");
            return;
        }

        request.setAttribute("subject_cd", subjectCd);
        request.setAttribute("subject_name", subjectName);

        request.getRequestDispatcher("/subject/subject_delete.jsp").forward(request, response);
    }

    // 削除処理 (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String subjectCd = request.getParameter("subject_cd");

        if (subjectCd == null || subjectCd.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/subject/SubjectList.action");
            return;
        }

        SubjectDAO dao = new SubjectDAO();

        Subject subject = dao.find(subjectCd);
        if (subject != null) {
            dao.delete(subjectCd);
        }

        request.getRequestDispatcher("/subject/subject_delete_done.jsp").forward(request, response);
    }
}
