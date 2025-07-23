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

        String year = toHalfWidth(request.getParameter("year"));
        String classNum = toHalfWidth(request.getParameter("classNum"));
        String subject = toHalfWidth(request.getParameter("subject"));
        String times = toHalfWidth(request.getParameter("times"));

        if (studentIds == null || scores == null) {
            request.setAttribute("error", "得点または学生情報が未入力です。");
            return "/kadai/scoreManagement.jsp";
        }

        List<Score> scoreList = new ArrayList<>();
        boolean hasError = false;

        for (int i = 0; i < studentIds.length; i++) {
            int point = 0;
            try {
                String scoreStr = toHalfWidth(scores[i]); // ★ 全角→半角
                point = Integer.parseInt(scoreStr);
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

            // 🔁 再検索して scoreList をセットし直す
            ScoreDAO dao = new ScoreDAO();
            List<Score> refreshed = dao.search(year, classNum, subject, times);

            request.setAttribute("scoreList", refreshed);
            request.setAttribute("searchPerformed", true);
            return "/kadai/scoreManagement.jsp";
        }

        // ✅ 成績登録処理
        ScoreDAO dao = new ScoreDAO();
        dao.register(scoreList, subject, times);

        // ✅ 登録後の再検索
        List<Score> refreshed = dao.search(year, classNum, subject, times);
        request.setAttribute("scoreList", refreshed);
        request.setAttribute("searchPerformed", true);
        request.setAttribute("message", "登録が完了しました。");

        return "/kadai/registerComplete.jsp";
    }

    private String toHalfWidth(String input) {
        if (input == null) return null;
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (c >= '０' && c <= '９') {
                sb.append((char)(c - '０' + '0'));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}