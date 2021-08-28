package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.users.Login;
import model.users.LoginLogic;
import model.users.Users;

/**
 * ログイン処理の遷移
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		Users users = null;
		if (!("".equals(mail)) || !("".equals(pass))) {

			Login login = new Login(mail, pass);
			LoginLogic bo = new LoginLogic();
			users = bo.execute(login);
		}

		if (users != null) {
			HttpSession session = request.getSession();
			session.setAttribute("users", users);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginResult.jsp");
		dispatcher.forward(request, response);

	}

}
