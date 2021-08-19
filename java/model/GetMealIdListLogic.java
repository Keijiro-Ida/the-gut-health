package model;

import java.util.ArrayList;

public class GetMealIdListLogic {
	public ArrayList<Integer> execute(DefaultPlanAndResult defaultSetting) {
		ArrayList<Integer> mealIdList = new ArrayList<>();
		if (defaultSetting != null) {

			mealIdList.add(defaultSetting.getBreakfastId());
			mealIdList.add(defaultSetting.getAmSnackID());
			mealIdList.add(defaultSetting.getLunchId());
			mealIdList.add(defaultSetting.getPmSnackId());
			mealIdList.add(defaultSetting.getDinnerId());
			mealIdList.add(defaultSetting.getNightSnackId());
		}

		return mealIdList;
	}
}
