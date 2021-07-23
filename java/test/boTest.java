package test;

import java.sql.Timestamp;

import model.CreateDateLogic;
import model.DateMealAll;
import model.GetDateLogic;
import model.MealAct;
import model.PostDate;
import model.PostMealAct;
import model.PostMealActLogic;
import model.updateDateMealAllLogic;
import model.users.Users;

public class boTest {

	public static void main(String[] args) {
		//testExecute1();
		testExecute2();

	}

	public static void testExecute1() {

		Users users = new Users(1, "11223344", "idatt1122@gmail.com");
		GetDateLogic bo = new GetDateLogic();
		DateMealAll date = bo.execute(users);
		if (date == null) {
			PostDate postDate = new PostDate(users.getUsrId(), java.sql.Date.valueOf("2021-07-23"));
			CreateDateLogic bo2 = new CreateDateLogic();
			DateMealAll dateNew = bo2.execute(postDate);
			if (dateNew != null) {
				System.out.println("date新規作成 dateId:" + dateNew.getDateId());
			} else {
				System.out.println("失敗");
			}
		} else {
			System.out.println("成功:dateId:" + date.getDateId());
		}
	}

	public static void testExecute2() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		PostMealAct act = new PostMealAct(1, now, 1, "ポテトサラダ");
		PostMealActLogic bo = new PostMealActLogic();
		MealAct mealAct = bo.execute(act);
		if (mealAct != null) {
			System.out.println("成功");
			Users users = new Users(1, "11223344", "idatt1122@gmail.com");
			GetDateLogic bo2 = new GetDateLogic();
			DateMealAll date = bo2.execute(users);
			date.setActIdBreakFast(mealAct.getActId());
			updateDateMealAllLogic bo3 = new updateDateMealAllLogic();
			int result = bo3.execute(date);
			if (result == 1) {
				System.out.println("Date更新成功");
			}
		} else {
			System.out.println("失敗");
		}
	}
}
