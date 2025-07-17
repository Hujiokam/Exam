package kadai;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDAO;
import tool.Action;

public class searchScoreAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String year = request.getParameter("year");
        String classNum = request.getParameter("classNum");  // ここがポイント
        String subject = request.getParameter("subject");
        String times = request.getParameter("times");

        ScoreDAO dao = new ScoreDAO();
        List<Score> scoreList = dao.search(year, classNum, subject, times);

        request.setAttribute("scoreList", scoreList);
        request.setAttribute("searchPerformed", true); // 検索済みフラグ

        return "/kadai/scoreManagement.jsp";  // 検索結果表示ページへ
    }
}
