package subject;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectList.action")
public class SubjectList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        try {
            SubjectDAO dao = new SubjectDAO();
            List<Subject> list = dao.getAllSubjects(); // これ重要
            request.setAttribute("subjects", list);

            // JSPにフォワード
            request.getRequestDispatcher("/subject/subject_list.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
