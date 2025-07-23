package kadai;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;

@WebServlet("/student/StudentDelete.action")
public class StudentDelete extends HttpServlet {

    // 確認画面表示 (GET)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentNo = request.getParameter("no");
        String studentName = request.getParameter("name");

        if (studentNo == null || studentName == null) {
            response.sendRedirect(request.getContextPath() + "/kadai/StudentList.action");
            return;
        }

        request.setAttribute("student_no", studentNo);
        request.setAttribute("student_name", studentName);

        request.getRequestDispatcher("/kadai/student_delete.jsp").forward(request, response);
    }

    // 削除処理 (POST)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String studentNo = request.getParameter("student_no");

        if (studentNo == null || studentNo.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/kadai/StudentList.action");
            return;
        }

        StudentDAO dao = new StudentDAO();
        Student student = dao.find(studentNo);  // これが null でなければ削除
        if (student != null) {
            dao.delete(studentNo);
        }

        request.getRequestDispatcher("/kadai/student_delete_done.jsp").forward(request, response);
    }
}