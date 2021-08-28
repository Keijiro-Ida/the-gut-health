package model;

public class ScoreMessage {

	static String[] msg35 = { "がんばれ〜！", "ファイト!!", "ガンバ！！", "スキマ時間取りましょう！" };
	static String[] msg45 = { "がんばってますね", "継続してきましょう！", "腸のツルツルレベル上昇中です。", "ナイスファイトです!" };
	static String[] msg55 = { "素晴らしいです!", "よくがんばりました！", "腸が歓喜しています！", "すごい!継続しましょう！" };
	static String[] msg65 = { "あなたの腸はアスリートレベルです", "素晴らしい!!", "スゴッ", "ヤバッ", "腸が喜んでます!!", "あなたすごいですね。", "半端ない!",
			"あなたの腸はつるつるです。" };
	static String[] msg75 = { "あなたの腸は超人レベルです", "鬼ヤバッ", "半端ないッ!!", "あなたの腸は最強です。" };
	static int num35 = (int) (Math.random() * (msg35.length));
	static int num45 = (int) (Math.random() * (msg45.length));
	static int num55 = (int) (Math.random() * (msg55.length));
	static int num65 = (int) (Math.random() * (msg65.length));
	static int num75 = (int) (Math.random() * (msg75.length));

	public static String getMsg35() {
		return msg35[num35];
	}

	public static String getMsg45() {
		return msg45[num45];
	}

	public static String getMsg55() {
		return msg55[num55];
	}

	public static String getMsg65() {
		return msg65[num65];
	}

	public static String getMsg75() {
		return msg75[num75];
	}

}
