package kadai;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Score;
import dao.ScoreDAO;
import tool.Action;

public class registerScoreAction extends Action {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");

        String[] studentIds = request.getParameterValues("studentId");
        String[] scores = request.getParameterValues("score");

        String year = request.getParameter("year");
        String classNum = request.getParameter("classNum");
        String subject = request.getParameter("subject");
        String times = request.getParameter("times");


        if (studentIds == null || scores == null) {
            request.setAttribute("error", "得点または学生情報が未入力です。");
            return "/kadai/scoreManagement.jsp";
        }

        List<Score> scoreList = new ArrayList<>();
        boolean hasError = false;

        for (int i = 0; i < studentIds.length; i++) {
            int point = 0;
            try {
                point = Integer.parseInt(scores[i]);
                if (point < 0 || point > 100) {
                    hasError = true;
                    break;
                }
            } catch (NumberFormatException e) {
                hasError = true;
                break;
            }

            Score s = new Score();
            s.setStudentId(studentIds[i]);
            s.setScore(point);
            scoreList.add(s);
        }

        if (hasError) {
            request.setAttribute("error", "得点は0～100の範囲で入力してください。");
            request.setAttribute("scoreList", scoreList);
            request.setAttribute("searchPerformed", true);
            return "/kadai/scoreManagement.jsp";
        }

        // 登録処理
        ScoreDAO dao = new ScoreDAO();
        dao.register(scoreList, subject, times);

        // 再検索して結果表示
        List<Score> refreshed = dao.search(year, classNum, subject, times);
        request.setAttribute("scoreList", refreshed);
        request.setAttribute("searchPerformed", true);
        request.setAttribute("message", "登録が完了しました。");

        return "/kadai/registerComplete.jsp";
    }
}
