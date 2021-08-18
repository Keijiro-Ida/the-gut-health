package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MyCalendar;
import model.MyCalendarLogic;
import model.users.Users;

/**
 * Servlet implementation class MonthGraphServlet
 */
@WebServlet("/MonthGraphServlet")
public class MonthGraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");

		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");

		MyCalendarLogic myCalendarLogic = new MyCalendarLogic();
		MyCalendar mc = null;
		if (s_year != null && s_month != null) {
			int year = Integer.parseInt(s_year);
			int month = Integer.parseInt(s_month);
			if (month == 0) {
				month = 12;
				year--;
			}
			if (month == 13) {
				month = 1;
				year++;
			}
			try {
				mc = myCalendarLogic.createMyCalendar(year, month, users);
			} catch (SQLException e) {
				response.sendRedirect("/WEB-INF/jsp/error.jsp");
			}

		} else {
			mc = (MyCalendar) session.getAttribute("mc");
		}
		session.setAttribute("mc", mc);

		ArrayList<String> dateList = new ArrayList<>();
		ArrayList<Integer> scoreList = new ArrayList<>();
		for (int i = 0; i < mc.getData().length; i++) {
			for (int j = 0; j < 7; j++) {
				if (mc.getData()[i][j] != "") {
					mc.getData()[i][j] = mc.getData()[i][j].replace("*", "");
					//String date = mc.getMonth() + "月" + mc.getData()[i][j] + "日";
					dateList.add(mc.getData()[i][j]);
					if (mc.getPlanAndResult_cal()[i][j] == null) {
						int score = 0;
						scoreList.add(score);
					} else {
						scoreList.add(mc.getPlanAndResult_cal()[i][j].getScore());
					}
				}
			}
		}

		request.setAttribute("dateList", dateList);
		request.setAttribute("scoreList", scoreList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/monthGraph.jsp");
		dispatcher.forward(request, response);
	}

}
