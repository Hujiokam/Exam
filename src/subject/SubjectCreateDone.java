package subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectCreateDone.action")
public class SubjectCreateDone extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            request.setCharacterEncoding("UTF-8");

            String cd = request.getParameter("cd");
            String name = request.getParameter("name");

            Subject subject = new Subject();
            subject.setCode(cd);
            subject.setName(name);

            SubjectDAO dao = new SubjectDAO();
            dao.insert(subject);

            // 完了画面へフォワード
            request.getRequestDispatcher("/subject/subject_createdone.jsp")
                   .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }

    // GETで来た場合はPOSTにリダイレクト
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/subject/SubjectList.action");
    }
}
