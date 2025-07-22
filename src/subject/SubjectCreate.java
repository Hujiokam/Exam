package subject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDAO;

@WebServlet("/subject/SubjectCreate.action")
public class SubjectCreate extends HttpServlet {

    // フォーム表示 (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // 学校コードを固定値で設定
        String schoolCd = "oom";
        request.setAttribute("school_cd", schoolCd);

        // 空の値やエラーメッセージをセットしてjspへ
        request.setAttribute("cd", "");
        request.setAttribute("name", "");
        request.setAttribute("error", "");

        request.getRequestDispatcher("/subject/subject_create.jsp").forward(request, response);
    }

    // 登録処理 (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");
        String schoolCd = request.getParameter("school_cd");

        if (cd == null || name == null || schoolCd == null) {
            request.setAttribute("error", "入力値が不正です");
            request.getRequestDispatcher("/subject/subject_create.jsp").forward(request, response);
            return;
        }

        cd = cd.trim();
        name = name.trim();
        schoolCd = schoolCd.trim();

        if (cd.length() != 3) {
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("school_cd", schoolCd);
            request.setAttribute("error", "科目コードは3文字で打ってください");
            request.getRequestDispatcher("/subject/subject_create.jsp").forward(request, response);
            return;
        }

        if (!cd.matches("^[a-zA-Z0-9]+$")) {
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("school_cd", schoolCd);
            request.setAttribute("error", "科目コードは半角英数字のみで入力してください");
            request.getRequestDispatcher("/subject/subject_create.jsp").forward(request, response);
            return;
        }

        SubjectDAO dao = new SubjectDAO();

        if (dao.find(cd) != null) {
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.setAttribute("school_cd", schoolCd);
            request.setAttribute("error", "科目コードが重複しています");
            request.getRequestDispatcher("/subject/subject_create.jsp").forward(request, response);
            return;
        }

        Subject subject = new Subject();
        subject.setCode(cd);
        subject.setName(name);
        subject.setSchoolCode(schoolCd);

        dao.insert(subject);

        request.getRequestDispatcher("/subject/SubjectCreateDone.action").forward(request, response);
    }
}
