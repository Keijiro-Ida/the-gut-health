package model;

public class GetDigestionMinutes_strLogic {
	public String[] execute(long[] digestionMinutes) {
		String[] digestionMinutes_str = new String[12];
		for (int i = 0; i < 12; i++) { //消化時間文字列の獲得
			if (digestionMinutes[i] != 0) {
				long hour = digestionMinutes[i] / 60;
				long minutes = digestionMinutes[i] - hour * 60;
				digestionMinutes_str[i] = hour + "時間" + minutes + "分";
			}
		}
		return digestionMinutes_str;
	}
}
