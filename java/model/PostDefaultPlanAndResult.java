package model;

public class PostDefaultPlanAndResult {
	private int usrId;
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

	public PostDefaultPlanAndResult() {
	}

	public PostDefaultPlanAndResult(int usrId, int breakfastId, String breakfastTime,
			int amSnackId, String amSnackTime, int lunchId, String lunchTime,
			int pmSnackId, String pmSnackTime, int dinnerId, String dinnerTime,
			int nightSnackId, String nightSnackTime) {
		this.usrId = usrId;
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

	public int getBreakfastId() {
		return breakfastId;
	}

	public String getBreakfastTime() {
		return breakfastTime;
	}

	public int getAmSnackId() {
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

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	public void setBreakfastId(int breakfastId) {
		this.breakfastId = breakfastId;
	}

	public void setBreakfastTime(String breakfastTime) {
		this.breakfastTime = breakfastTime;
	}

	public void setAmSnackId(int id) {
		this.amSnackId = id;
	}

	public void setAmSnackTime(String time) {
		this.amSnackTime = time;
	}

	public void setLunchId(int id) {
		this.lunchId = id;
	}

	public void setLunchTime(String time) {
		this.lunchTime = time;
	}

	public void setPmSnackId(int id) {
		this.pmSnackId = id;
	}

	public void setPmSnackTime(String time) {
		this.pmSnackTime = time;
	}

	public void setDinnerId(int id) {
		this.dinnerId = id;
	}

	public void setDinnerTime(String time) {
		this.dinnerTime = time;
	}

	public void setNightSnackId(int id) {
		this.nightSnackId = id;
	}

	public void setNightSnackTime(String time) {
		this.nightSnackTime = time;
	}

}
