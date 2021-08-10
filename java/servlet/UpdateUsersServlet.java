package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.users.UpdateUsersLogic;
import model.users.Users;

/**
 * Servlet implementation class UpdateUsersServlet
 */
@WebServlet("/UpdateUsersServlet")
public class UpdateUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateUsers.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");

		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");
		int result = 0;

		if (pass.equals(pass2) && !("".equals(pass)) && !("".equals(mail))) {
			users.setMail(mail);
			users.setPass(pass);
			UpdateUsersLogic logic = new UpdateUsersLogic();
			result = logic.execute(users);
			session.setAttribute("users", users);
		} else {
			request.setAttribute("errMsg", "入力値エラー");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateUsersResult.jsp");
			dispatcher.forward(request, response);
		}

		if (result == 1) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateUsersResult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("errMsg", "システムエラー");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/updateUsersResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
