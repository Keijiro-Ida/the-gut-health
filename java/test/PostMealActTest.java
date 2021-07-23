package test;

import java.sql.Timestamp;

import DAO.DateMealAllDAO;
import DAO.MealActDAO;
import model.DateMealAll;
import model.MealAct;
import model.PostDate;
import model.PostMealAct;
import model.users.Users;

public class PostMealActTest {

	public static void main(String[] args) {
		//testExecute1();
		//testExecute2();
		//testExecute3();
		//testExecute4();
		//testExecute5();
	}

	public static void testExecute1() {
		MealActDAO dao = new MealActDAO();
		PostMealAct act = new PostMealAct(1, new Timestamp(System.currentTimeMillis() + 10000), 1, "サンドウィッチ");
		MealAct mealAct = dao.mealActCreate(act);
		if (mealAct != null) {
			System.out.println("成功 ID:" + mealAct.getActId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute2() {
		DateMealAllDAO dao = new DateMealAllDAO();
		PostDate date = new PostDate(1, new java.sql.Date(System.currentTimeMillis() - 1000000000));
		DateMealAll act = dao.createDateMealAll(date);
		if (act != null) {
			System.out.println("成功" + act.getDateId() + act.getDateToday());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute3() {
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		DateMealAllDAO dao = new DateMealAllDAO();
		DateMealAll actDate = dao.findByUsers(users);
		if (actDate != null) {
			System.out.println("成功" + actDate.getDateToday());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute4() {
		int actId = 1;
		MealActDAO dao = new MealActDAO();
		MealAct mealAct = dao.selectByActId(actId);
		if (mealAct != null) {
			System.out.println("成功" + mealAct.getActText());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute5() {

		PostDate date = new PostDate(1, java.sql.Date.valueOf("2021-07-11"));
		DateMealAllDAO dao = new DateMealAllDAO();
		DateMealAll actDate = dao.findByPostDate(date);
		actDate.setActIdBreakFast(1);
		actDate.setActIdSnackPlan(4);
		actDate.setActIdDinnerPlan(5);
		actDate.setScore(80);

		int result = dao.updateDateMealAll(actDate);
		if (result == 1) {
			System.out.println("成功");
		} else {
			System.out.println("失敗");
		}
	}
}
