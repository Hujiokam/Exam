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

        List<ScoreResult> scoreList = null;
        String subjectName = "";

        // 学生番号検索があれば優先
        if (studentId != null && !studentId.trim().isEmpty()) {
            scoreList = dao.searchByStudentId(studentId.trim());
            subjectName = "学生番号検索結果";
            request.setAttribute("subjectCodeVisible", true);  // JSPで科目コード列表示用フラグ
        } else if (year != null && !year.isEmpty() &&
                   classNum != null && !classNum.isEmpty() &&
                   subjectCode != null && !subjectCode.isEmpty()) {
            scoreList = dao.searchBySubject(year, classNum, subjectCode);

            // 科目コード → 科目名マップ
            switch(subjectCode) {
                case "A02": subjectName = "国語"; break;
                case "B01": subjectName = "Java"; break;
                default: subjectName = "指定なし"; break;
            }
            request.setAttribute("subjectCodeVisible", false);  // 科目コード列は非表示
        } else {
            // 入力不足などは空リスト返し、JSPで「該当なし」表示へ
            scoreList = java.util.Collections.emptyList();
            subjectName = "";
            request.setAttribute("subjectCodeVisible", false);
        }

        request.setAttribute("scoreList", scoreList);
        request.setAttribute("subjectName", subjectName);

        RequestDispatcher rd = request.getRequestDispatcher("/kadai/searchResult.jsp");
        rd.forward(request, response);
    }
}
