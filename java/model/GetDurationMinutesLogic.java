package model;

import java.time.Duration;
import java.util.ArrayList;

public class GetDurationMinutesLogic {
	public long[] execute(ArrayList<MealAct> mealActList) {
		long[] durationMinutes_long = new long[10];

		for (int j = 0; j < 10; j++) {
			if (mealActList.get(j) != null) {

				if (mealActList.get(j + 2) != null) {
					durationMinutes_long[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 2).getActTime()).toMinutes();

				} else if (j <= 7 && mealActList.get(j + 2) == null && mealActList.get(j + 4) != null) {
					durationMinutes_long[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 4).getActTime()).toMinutes();
				} else if (j <= 5 && mealActList.get(j + 2) == null && mealActList.get(j + 4) == null
						&& mealActList.get(j + 6) != null) {
					durationMinutes_long[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 6).getActTime()).toMinutes();
				} else if (j <= 3 && mealActList.get(j + 2) == null && mealActList.get(j + 4) == null
						&& mealActList.get(j + 6) == null && mealActList.get(j + 8) != null) {
					durationMinutes_long[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 8).getActTime()).toMinutes();
				} else if (j <= 1 && mealActList.get(j + 2) == null && mealActList.get(j + 4) == null
						&& mealActList.get(j + 6) == null && mealActList.get(j + 8) == null
						&& mealActList.get(j + 10) != null) {
					durationMinutes_long[j] = Duration
							.between(mealActList.get(j).getActTime(), mealActList.get(j + 10).getActTime()).toMinutes();
				}

			}

		}
		return durationMinutes_long;
	}

}
