package model;

public class GetDuration_strLogic {
	public String[] execute(long[] durationMinutes) {
		String[] durationMinutes_str = new String[10];
		for (int i = 0; i < 10; i++) { //スキマ時間文字列の獲得

			if (durationMinutes[i] != 0) {
				long hour = durationMinutes[i] / 60;
				long minutes = durationMinutes[i] - hour * 60;
				durationMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}
		return durationMinutes_str;
	}
}