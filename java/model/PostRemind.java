package model;

import java.sql.Timestamp;

public class PostRemind { //リマインド通知の情報
	private int mealActId; //食事行為ID
	private Timestamp remindTime; //リマインド時刻設定
	private String text; //リマインドするテキスト
	private String mail; //メールアドレス

	public PostRemind() {
	}

	public PostRemind(int mealActId, Timestamp remindTime, String text, String mail) {
		this.mealActId = mealActId;
		this.remindTime = remindTime;
		this.mail = mail;
		this.text = text;
	}

	public int getMealActId() {
		return mealActId;
	}

	public String getText() {
		return text;
	}

	public String getMail() {
		return mail;
	}

	public Timestamp getRemindTime() {
		return remindTime;
	}
}
