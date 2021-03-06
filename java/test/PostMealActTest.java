package test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.apache.log4j.Logger;

import DAO.MealActDAO;
import DAO.PlanAndResultDAO;
import model.MealAct;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostPlanAndResult;
import model.users.Users;

public class PostMealActTest {

	public static void main(String[] args) throws SQLException {
		//testExecute1();
		//testExecute2();
		//testExecute3();
		//testExecute4();
		//testExecute5();
		testExecute6();
	}

	public static void testExecute1() throws SQLException {
		MealActDAO dao = new MealActDAO();
		PostMealAct act = new PostMealAct(1, LocalDateTime.now(), 1, 1);
		MealAct mealAct = dao.mealActCreate(act);
		if (mealAct != null) {
			System.out.println("成功 ID:" + mealAct.getActId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute2() throws SQLException {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PostPlanAndResult date = new PostPlanAndResult(1, LocalDate.now());
		PlanAndResult act = dao.createPlanAndResult(date);
		if (act != null) {
			System.out.println("成功" + act.getPlanAndResultId() + act.getPlanAndResultDate());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute3() throws SQLException {
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult actDate = dao.findByUsers(users);
		if (actDate != null) {
			System.out.println("成功" + actDate.getPlanAndResultDate());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute4() throws SQLException {
		int actId = 1;
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.selectByActId(actId);
		if (mealAct != null) {
			System.out.println("成功" + mealAct.getPlanAndResultId() + " " + mealAct.getThreeMealsId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute5() throws SQLException {

		PostPlanAndResult date = new PostPlanAndResult(1, LocalDate.now());
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult actDate = dao.findByPostPlanAndResult(date);
		actDate.setActIdBreakfast(1);
		actDate.setActIdDinnerPlan(5);
		actDate.setScore(80);

		int result = dao.updatePlanAndResult(actDate);
		if (result == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute6() {
		Logger logger = Logger.getLogger("MyLogger");
		// ログ出力
		logger.info("This is info.");
	}
}
