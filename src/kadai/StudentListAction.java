package kadai;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDAO;
import tool.Action;


@WebServlet(urlPatterns={"/kadai/StudentListAction"})
public class StudentListAction extends Action {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	    String year = request.getParameter("f1");
	    String clas = request.getParameter("f2");
	    String status = request.getParameter("f3");

	    StudentDAO dao = new StudentDAO();
	    List<Student> list = dao.search(year, clas, status);

	    // 新たに取得
	    List<String> years = dao.getYears();
	    List<String> classes = dao.getClasses();

	    request.setAttribute("students", list);
	    request.setAttribute("count", list.size());
	    request.setAttribute("years", years);
	    request.setAttribute("classes", classes);

	    return "studentlist.jsp";
	}
}

