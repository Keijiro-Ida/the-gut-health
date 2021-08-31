package model;

import java.sql.Timestamp;

public class Remind { //リマインド通知の情報
	private int actId; //食事行為ID
	private int remindId; //リマインドID
	private Timestamp remindTime; //リマインド時刻設定
	private String text; //リマインドするテキスト
	private String mail; //メールアドレス

	public Remind() {
	}

	public Remind(int actId, int remindId, Timestamp remindTime, String text, String mail) {
		this.actId = actId;
		this.remindTime = remindTime;
		this.mail = mail;
		this.text = text;
		this.remindId = remindId;
	}

	public int getActId() {
		return actId;
	}

	public int getRemindId() {
		return remindId;
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
