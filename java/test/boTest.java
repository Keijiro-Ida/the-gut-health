package test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import DAO.PlanAndResultDAO;
import model.CreatePlanAndResultLogic;
import model.GetAverageScoreListLogic;
import model.GetMealGenreListLogic;
import model.GetMealListLogic;
import model.GetMonthPlanAndResultList;
import model.GetPlanAndResultByIdLogic;
import model.GetPlanAndResultByUsersLogic;
import model.Meal;
import model.MealAct;
import model.MealGenre;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostMealActLogic;
import model.PostPlanAndResult;
import model.UpdatePlanAndResultLogic;
import model.users.Users;

public class boTest {

	public static void main(String[] args) {
		//testExecute1();
		//testExecute2();
		//testExecute3();
		//testExecute4();
		//testExecute5();
		//testExecute6();
		//testExecute7();
		testExecute8();
	}

	public static void testExecute1() {

		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		GetPlanAndResultByUsersLogic bo = new GetPlanAndResultByUsersLogic();
		PlanAndResult date = bo.execute(users);
		if (date == null) {
			PostPlanAndResult postDate = new PostPlanAndResult(users.getUsrId(), LocalDate.now());
			CreatePlanAndResultLogic bo2 = new CreatePlanAndResultLogic();
			PlanAndResult planAndResult = bo2.execute(postDate);
			if (planAndResult != null) {
				System.out.println("date新規作成 dateId:" + planAndResult.getPlanAndResultId());
			} else {
				System.out.println("失敗");
			}
		} else {
			System.out.println("成功:dateId:" + date.getPlanAndResultId());
		}
	}

	public static void testExecute2() {
		PostMealAct act = new PostMealAct(1, LocalDateTime.now(), 2, 2);
		PostMealActLogic bo = new PostMealActLogic();
		MealAct mealAct = bo.execute(act);
		if (mealAct != null) {
			System.out.println("成功");
			Users users = new Users(1, "11223344", "idatt1122@gmail.com");
			GetPlanAndResultByUsersLogic bo2 = new GetPlanAndResultByUsersLogic();
			PlanAndResult date = bo2.execute(users);
			date.setActIdBreakfast(mealAct.getActId());
			UpdatePlanAndResultLogic bo3 = new UpdatePlanAndResultLogic();
			int result = bo3.execute(date);
			if (result == 1) {
				System.out.println("Date更新成功");
			}
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute3() {
		GetMealGenreListLogic bo4 = new GetMealGenreListLogic();
		ArrayList<MealGenre> list = bo4.execute();
		list.forEach(mealGenre -> System.out.println(mealGenre.getMealGenreName()));
	}

	public static void testExecute4() {
		GetMealListLogic bo5 = new GetMealListLogic();
		ArrayList<Meal> list2 = bo5.execute(1);
		list2.forEach(meal -> System.out.println(meal.getMealName()));
	}

	public static void testExecute5() {
		PlanAndResultDAO dao = new PlanAndResultDAO();
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		LocalDate localDate = LocalDate.now();
		ArrayList<PlanAndResult> list = dao.getMonthPlanAndResultList(users, localDate);
		list.forEach(p -> System.out.println("成功"));
	}

	public static void testExecute6() {
		GetMonthPlanAndResultList bo = new GetMonthPlanAndResultList();
		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		LocalDate localDate = LocalDate.now();
		ArrayList<PlanAndResult> list = bo.execute(users, localDate);
		list.forEach(p -> System.out.println(p.getScore()));
	}

	public static void testExecute7() {
		GetPlanAndResultByIdLogic bo = new GetPlanAndResultByIdLogic();
		PlanAndResult planAndResult = bo.execute(5);
		if (planAndResult != null) {
			System.out.println("成功" + planAndResult.getPlanAndResultDate());
		} else {
			System.out.println("失敗");
		}
	}

	public static void testExecute8() {
		GetAverageScoreListLogic bo = new GetAverageScoreListLogic();
		ArrayList<Integer> list = bo.execute(1, 2021);
		list.forEach(k -> System.out.println(k + " "));
	}
}
