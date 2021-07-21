package model;

import java.util.Date;

public class PostActDate {
	private int usrId;
	private Date actDate;

	public PostActDate(int usrId, Date actDate) {
		this.usrId = usrId;
		this.actDate = actDate;
	}

	public int getUsrId() {
		return usrId;
	}

	public Date getActDate() {
		return actDate;
	}
}
