package model;

import java.io.Serializable;

public class DefaultPlanAndResult implements Serializable {
	private int usrId;
	private int defaultId;
	private int breakfastId;
	private String breakfastTime;
	private int amSnackId;
	private String amSnackTime;
	private int lunchId;
	private String lunchTime;
	private int pmSnackId;
	private String pmSnackTime;
	private int dinnerId;
	private String dinnerTime;
	private int nightSnackId;
	private String nightSnackTime;

	public DefaultPlanAndResult() {
	}

	public DefaultPlanAndResult(int usrId, int defaultId, int breakfastId, String breakfastTime,
			int amSnackId, String amSnackTime, int lunchId, String lunchTime,
			int pmSnackId, String pmSnackTime, int dinnerId, String dinnerTime,
			int nightSnackId, String nightSnackTime) {
		this.usrId = usrId;
		this.defaultId = defaultId;
		this.breakfastId = breakfastId;
		this.breakfastTime = breakfastTime;
		this.lunchId = lunchId;
		this.lunchTime = lunchTime;
		this.amSnackId = amSnackId;
		this.amSnackTime = amSnackTime;
		this.pmSnackId = pmSnackId;
		this.pmSnackTime = pmSnackTime;
		this.dinnerId = dinnerId;
		this.dinnerTime = dinnerTime;
		this.nightSnackId = nightSnackId;
		this.nightSnackTime = nightSnackTime;
	}

	public int getUsrId() {
		return usrId;
	}

	public int getDefaultId() {
		return defaultId;
	}

	public int getBreakfastId() {
		return breakfastId;
	}

	public String getBreakfastTime() {
		return breakfastTime;
	}

	public int getAmSnackID() {
		return amSnackId;
	}

	public String getAmSnackTime() {
		return amSnackTime;
	}

	public int getLunchId() {
		return lunchId;
	}

	public String getLunchTime() {
		return lunchTime;
	}

	public int getPmSnackId() {
		return pmSnackId;
	}

	public String getPmSnackTime() {
		return pmSnackTime;
	}

	public int getDinnerId() {
		return dinnerId;
	}

	public String getDinnerTime() {
		return dinnerTime;
	}

	public int getNightSnackId() {
		return nightSnackId;
	}

	public String getNightSnackTime() {
		return nightSnackTime;
	}
}
