package model;

public class ActDetail {
	private int actDetailId;
	private String actDetailString;

	public ActDetail(int actDetailId, String actDetailString) {
		this.actDetailId = actDetailId;
		this.actDetailString = actDetailString;
	}

	public int getActDetailid() {
		return actDetailId;
	}

	public String getActDetailString() {
		return actDetailString;
	}
}
