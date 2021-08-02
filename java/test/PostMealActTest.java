package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import DAO.MealActDAO;
import DAO.PlanAndResultDAO;
import model.MealAct;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostPlanAndResult;
import model.users.Users;

public class PostMealActTest {

	public static void main(String[] args) {
		//testExecute1();
		//testExecute2();
		//testExecute3();
		//testExecute4();
		testExecute5();
	}

	public static void testExecute1() {
		MealActDAO dao = new MealActDAO();
		PostMealAct act = new PostMealAct(1, 1, LocalDateTime.now(), 1, 1);
		MealAct mealAct = dao.mealActCreate(act);
		if (mealAct != null) {
			System.out.println("成功 ID:" + mealAct.getActId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute2() {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PostPlanAndResult date = new PostPlanAndResult(1, LocalDate.now());
		PlanAndResult act = dao.createPlanAndResult(date);
		if (act != null) {
			System.out.println("成功" + act.getPlanAndResultId() + act.getDate());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute3() {
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult actDate = dao.findByUsers(users);
		if (actDate != null) {
			System.out.println("成功" + actDate.getDate());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute4() {
		int actId = 1;
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.selectByActId(actId);
		if (mealAct != null) {
			System.out.println("成功" + mealAct.getPlanAndResultId() + " " + mealAct.getThreeMealsId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute5() {

		PostPlanAndResult date = new PostPlanAndResult(1, LocalDate.now());
		PlanAndResultDAO dao = new PlanAndResultDAO();
		PlanAndResult actDate = dao.findByPostPlanAndResult(date);
		actDate.setActIdBreakFast(1);
		actDate.setActIdSnackPlan(4);
		actDate.setActIdDinnerPlan(5);
		actDate.setScore(80);

		int result = dao.updatePlanAndResult(actDate);
		if (result == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失敗");
		}
	}
}
