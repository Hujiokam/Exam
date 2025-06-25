package kadai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDAO;
import tool.Action;

public class LoginExecuteAction extends Action {
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception {
		HttpSession session=request.getSession();

		String id=request.getParameter("id");
		String password=request.getParameter("password");
		String name=request.getParameter("name");
		String school_cd=request.getParameter("school_cd");
		TeacherDAO dao=new TeacherDAO();
		Teacher teacher=dao.search(id, password, name, school_cd);

		if(teacher!=null){
			session.setAttribute("teacher", teacher);
			return "login-out.jsp";
		}
		return "login-error.jsp";

	}
}