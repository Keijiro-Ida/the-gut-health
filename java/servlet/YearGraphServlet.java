package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetAverageScoreListLogic;
import model.MyCalendar;
import model.MyCalendarLogic;
import model.users.Users;

/**
 * 年グラフ画面への遷移
 */
@WebServlet("/YearGraphServlet")
public class YearGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");

			String s_year = request.getParameter("year");
			String s_month = request.getParameter("month");

			MyCalendarLogic myCalendarLogic = new MyCalendarLogic();
			MyCalendar mc = null;
			if (s_year != null && s_month != null) {
				int year = Integer.parseInt(s_year);
				int month = Integer.parseInt(s_month);
				mc = myCalendarLogic.createMyCalendar(year, month, users);

			} else {
				mc = (MyCalendar) session.getAttribute("mc");
			}
			session.setAttribute("mc", mc);

			ArrayList<Integer> monthList = new ArrayList<>();
			for (int i = 1; i <= 12; i++) {
				monthList.add(i);
			}

			GetAverageScoreListLogic bo = new GetAverageScoreListLogic();
			ArrayList<Integer> averageScoreList = bo.execute(users.getUsrId(), mc.getYear());

			request.setAttribute("monthList", monthList);
			request.setAttribute("averageScoreList", averageScoreList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/yearGraph.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			response.sendRedirect("/the-gut-healthy/error.jsp");
		}
	}

}
