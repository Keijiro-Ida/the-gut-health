package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class MealAct implements Serializable {
	private int usrId;
	private int actId;
	private Timestamp actTime;
	private int actDetailId;
	private String actText;

	public MealAct() {
	}

	public MealAct(int usrId, int actId, Timestamp actTime, int actDetailId, String actText) {
		this.usrId = usrId;
		this.actId = actId;
		this.actTime = actTime;
		this.actDetailId = actDetailId;
		this.actText = actText;
	}

	public int getUsrId() {
		return usrId;
	};

	public int getActId() {
		return actId;
	};

	public Timestamp getActTime() {
		return actTime;
	};

	public int getActDetailid() {
		return actDetailId;
	};

	public String getActText() {
		return actText;
	};
}