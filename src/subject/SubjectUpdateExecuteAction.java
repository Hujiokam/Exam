package subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectUpdateExecute.action")
public class SubjectUpdateExecuteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String code = request.getParameter("subject_cd");
        String name = request.getParameter("name");
        String schoolCd = "oom";  // 固定値

        if (code == null || name == null || code.isEmpty() || name.isEmpty()) {
            request.setAttribute("error", "未入力の項目があります");
            request.getRequestDispatcher("/subject/subject_update.jsp").forward(request, response);
            return;
        }

        Subject subject = new Subject();
        subject.setCode(code);
        subject.setName(name);
        subject.setSchoolCode(schoolCd);

        SubjectDAO dao = new SubjectDAO();
        dao.update(subject);

        request.getRequestDispatcher("/subject/subject_update_done.jsp").forward(request, response);
    }
}