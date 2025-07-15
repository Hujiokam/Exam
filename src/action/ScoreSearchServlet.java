package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDAO;

@WebServlet("/searchScore.action")
public class ScoreSearchServlet extends HttpServlet {
    private ScoreDAO dao = new ScoreDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String year = request.getParameter("year");
        String className = request.getParameter("class");
        String subject = request.getParameter("subject");
        String times = request.getParameter("times");

        List<Score> scoreList = dao.search(year, className, subject, times);
        request.setAttribute("scoreList", scoreList);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/scoreManagement.jsp");
        rd.forward(request, response);
    }
}