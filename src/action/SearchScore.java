package action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ScoreResult;
import dao.ScoreSearchDAO;

@WebServlet("/SearchScore")
public class SearchScore extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ScoreSearchDAO dao = new ScoreSearchDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String year = request.getParameter("year");
        String classNum = request.getParameter("classNum");
        String subjectCode = request.getParameter("subjectCode");
        String studentId = request.getParameter("studentId");

        List<ScoreResult> scoreList = dao.searchBySubject(year, classNum, subjectCode);

        // 科目コード → 科目名
        String subjectName = "個別検索";
        if ("A02".equals(subjectCode)) {
            subjectName = "国語";
        } else if ("B01".equals(subjectCode)) {
            subjectName = "Java";
        }

        request.setAttribute("scoreList", scoreList);
        request.setAttribute("subjectName", subjectName);

        RequestDispatcher rd = request.getRequestDispatcher("/kadai/searchResult.jsp");
        rd.forward(request, response);
    }
}