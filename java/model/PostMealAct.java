package model;

import java.sql.Timestamp;

public class PostMealAct {
	private int usrId;
	private Timestamp actTime;
	private int actDetailId;
	private String actText;

	public PostMealAct() {
	}

	public PostMealAct(int usrId, Timestamp actTime, int actDetailId, String actText) {
		this.usrId = usrId;
		this.actTime = actTime;
		this.actDetailId = actDetailId;
		this.actText = actText;
	}

	public int getUsrId() {
		return usrId;

	}

	public Timestamp getActTime() {
		return actTime;
	}

	public int getActDetailId() {
		return actDetailId;
	}

	public String getActText() {
		return actText;
	}
}
