package model;

import java.sql.Date;

public class PostDate {
	private int usrId;
	private Date dateToday;

	public PostDate(int usrId, Date dateToday) {
		this.usrId = usrId;
		this.dateToday = dateToday;
	}

	public int getUsrId() {
		return usrId;
	}

	public Date getDateToday() {
		return dateToday;
	}
}
