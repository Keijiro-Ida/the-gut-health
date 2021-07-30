package test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.CreatePlanAndResultLogic;
import model.GetPlanAndResultByUsersLogic;
import model.MealAct;
import model.PlanAndResult;
import model.PostMealAct;
import model.PostMealActLogic;
import model.PostPlanAndResult;
import model.UpdatePlanAndResultLogic;
import model.users.Users;

public class boTest {

	public static void main(String[] args) {
		//testExecute1();
		testExecute2();

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
		PostMealAct act = new PostMealAct(1, 1, LocalDateTime.now(), 2, 2);
		PostMealActLogic bo = new PostMealActLogic();
		MealAct mealAct = bo.execute(act);
		if (mealAct != null) {
			System.out.println("成功");
			Users users = new Users(1, "11223344", "idatt1122@gmail.com");
			GetPlanAndResultByUsersLogic bo2 = new GetPlanAndResultByUsersLogic();
			PlanAndResult date = bo2.execute(users);
			date.setActIdBreakFast(mealAct.getActId());
			UpdatePlanAndResultLogic bo3 = new UpdatePlanAndResultLogic();
			int result = bo3.execute(date);
			if (result == 1) {
				System.out.println("Date更新成功");
			}
		} else {
			System.out.println("失敗");
		}
	}
}
