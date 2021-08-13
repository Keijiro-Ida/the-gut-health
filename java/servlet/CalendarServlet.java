package servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.GetPlanAndResultByUsersLogic;
import model.MyCalendar;
import model.MyCalendarLogic;
import model.PlanAndResult;
import model.users.Users;

/**
 * Servlet implementation class CalendarServlet
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Users users = (Users) session.getAttribute("users");

		String s_year = request.getParameter("year");
		String s_month = request.getParameter("month");

		Calendar now = Calendar.getInstance();
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
			mc = myCalendarLogic.createMyCalendar(year, month, users);

		} else {
			mc = myCalendarLogic.createMyCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH + 1), users);
		}
		GetPlanAndResultByUsersLogic bo3 = new GetPlanAndResultByUsersLogic();
		PlanAndResult todayPlanAndResult = bo3.execute(users);

		request.setAttribute("todayPlanAndResultId", todayPlanAndResult.getPlanAndResultId());

		session.setAttribute("mc", mc);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
