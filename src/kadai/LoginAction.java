package kadai;
//コメント
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"/kadai/LoginAction"})
public class LoginAction extends HttpServlet {
	public void doGet (
			HttpServletRequest request, HttpServletResponse response
			)throws ServletException, IOException {
		request.getRequestDispatcher("login-in.jsp")
		.forward(request, response);
	}
}