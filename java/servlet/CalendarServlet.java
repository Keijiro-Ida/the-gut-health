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
 * カレンダー画面への遷移
 */
@WebServlet("/CalendarServlet")
public class CalendarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			Users users = (Users) session.getAttribute("users");

			String s_year = request.getParameter("year"); //カレンダーの年を獲得
			String s_month = request.getParameter("month"); //月を獲得

			Calendar now = Calendar.getInstance(); //現在の時間のカレンダーインスタンスを獲得
			MyCalendarLogic myCalendarLogic = new MyCalendarLogic();
			MyCalendar mc = null;

			//入力値がある場合の設定
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

				//入力値がない場合は現在時間で取得
			} else {
				mc = myCalendarLogic.createMyCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH + 1), users);
			}
			GetPlanAndResultByUsersLogic bo3 = new GetPlanAndResultByUsersLogic();
			PlanAndResult todayPlanAndResult = bo3.execute(users);

			request.setAttribute("todayPlanAndResultId", todayPlanAndResult.getPlanAndResultId());

			session.setAttribute("mc", mc);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/calendar.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/the-gut-healthy/error.jsp");
		}

	}
}
