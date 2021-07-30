package model;

import java.time.LocalDate;

public class PostPlanAndResult {
	private int usrId;
	private LocalDate date;

	public PostPlanAndResult(int usrId, LocalDate date) {
		this.usrId = usrId;
		this.date = date;
	}

	public int getUsrId() {
		return usrId;
	}

	public LocalDate getDate() {
		return date;
	}
}
