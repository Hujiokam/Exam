package subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectUpdate.action")
public class SubjectUpdateAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            response.sendRedirect("subject_list.jsp");
            return;
        }

        SubjectDAO dao = new SubjectDAO();
        Subject subject = dao.find(code);

        if (subject == null) {
            request.setAttribute("error", "該当の科目が見つかりませんでした");
            request.getRequestDispatcher("/subject/subject_list.jsp").forward(request, response);
            return;
        }

        request.setAttribute("subject", subject);
        request.getRequestDispatcher("/subject/subject_update.jsp").forward(request, response);
    }
}