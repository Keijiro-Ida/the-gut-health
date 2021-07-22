package test;

import java.sql.Timestamp;

import DAO.ActDateDAO;
import DAO.MealActDAO;
import model.ActDate;
import model.MealAct;
import model.PostActDate;
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
		PostMealAct act = new PostMealAct(1, new Timestamp(System.currentTimeMillis() + 10000), 2, "ラーメン");
		MealAct mealAct = dao.mealActCreate(act);
		if (mealAct != null) {
			System.out.println("成功 ID:" + mealAct.getActId());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute2() {
		ActDateDAO dao = new ActDateDAO();
		PostActDate date = new PostActDate(1, new java.sql.Date(System.currentTimeMillis()));
		ActDate act = dao.actDateCreate(date);
		if (act != null) {
			System.out.println("成功" + act.getActDateId() + act.getActDateToday());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute3() {
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		ActDateDAO dao = new ActDateDAO();
		ActDate actDate = dao.findByUsers(users);
		if (actDate != null) {
			System.out.println("成功" + actDate.getActDateToday());
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
	/*
	public static void testExecute5() {
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		ActDateDAO dao = new ActDateDAO();
		ActDate actDate = dao.findByUsers(users);
		actDate.setActIdBreakFast(1);
		actDate.setActIdLunch(1);
		actDate.setActIdSnack(2);
		actDate.setActIdDinner(2);
		actDate.setActIdBreakFastPlan(3);
		actDate.setActIdLunchPlan(2);
		actDate.setActIdSnackPlan(1);
		actDate.setActIdDinnerPlan(1);
		actDate.setScore(80);
	
		int result = dao.updateActDate(actDate);
		ActDate actDate2 = dao.findByUsers(users);
		if (result == 1) {
			System.out.println(actDate2.getActDateId());
			System.out.println("成功: actDate2.getActIdBreakfast()" + actDate2.getActIdBreakfast());
		} else {
			System.out.println("失敗");
		}
	}
	*/
}
