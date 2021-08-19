package model;

import java.util.ArrayList;

import model.DefaultPlanAndResult;

public class GetTimeListLogic {

	public ArrayList<String> execute(DefaultPlanAndResult defaultSetting) {
		ArrayList<String> timeList = new ArrayList<>();
		if (defaultSetting != null) {

			timeList.add(defaultSetting.getBreakfastTime());
			timeList.add(defaultSetting.getAmSnackTime());
			timeList.add(defaultSetting.getLunchTime());
			timeList.add(defaultSetting.getPmSnackTime());
			timeList.add(defaultSetting.getDinnerTime());
			timeList.add(defaultSetting.getNightSnackTime());
		}

		return timeList;
	}

}
