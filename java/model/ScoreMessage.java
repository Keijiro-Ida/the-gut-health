package model;

public class ScoreMessage {

	static String[] msg60 = { "がんばってますね", "がんばれ〜！", "もう少し!", "もう少しでツルツルの腸が!", "ファイト!!", "ガンバ！！", "あとちょっとです！！" };
	static String[] msg80 = { "がんばてますね", "継続してきましょう！", "すばらしい!", "腸のツルツルレベル上昇中" };
	static String[] msg100 = { "素晴らしいです!", "よくがんばりました！", "腸が歓喜しています！", "すごい!継続しましょう！" };
	static String[] msg120 = { "あなたの腸はアスリートレベルです", "素晴らしい!!", "スゴッ", "ヤバッ", "腸が喜んでます!!", "あなたすごいですね。", "半端ない!",
			"あなたの腸はつるつるです。" };
	static String[] msg140 = { "あなたの腸は超人レベルです", "鬼ヤバッ", "半端ないッ!!", "あなたの腸は最強です" };
	static int num60 = (int) (Math.random() * (msg60.length));
	static int num80 = (int) (Math.random() * (msg80.length));
	static int num100 = (int) (Math.random() * (msg100.length));
	static int num120 = (int) (Math.random() * (msg120.length));
	static int num140 = (int) (Math.random() * (msg140.length));

	public static String getMsg60() {
		return msg60[num60];
	}

	public static String getMsg80() {
		return msg80[num80];
	}

	public static String getMsg100() {
		return msg100[num100];
	}

	public static String getMsg120() {
		return msg120[num120];
	}

	public static String getMsg140() {
		return msg140[num140];
	}

}
