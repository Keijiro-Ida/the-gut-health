package model;

import java.sql.Date;

public class PostActDate {
	private int usrId;
	private Date actDateToday;

	public PostActDate(int usrId, Date actDateToday) {
		this.usrId = usrId;
		this.actDateToday = actDateToday;
	}

	public int getUsrId() {
		return usrId;
	}

	public Date getActDateToday() {
		return actDateToday;
	}
}
