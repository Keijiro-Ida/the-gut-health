package model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailLogic { //リマインド通知を行うクラス
	public Remind remind; //リマインド通知の情報
	public ScheduledExecutorService service; //リマインド通知を送るスレッド
	public ScheduledFuture<?> sf; //リマインド通知を送るスレッドの戻り値,キャンセルを行うためのインスタンス。
	public static HashMap<Integer, ScheduledFuture<?>> futureMap = new HashMap<Integer, ScheduledFuture<?>>(); //remindIdから、スレッドのキャンセルを行うためのmap
	public static HashMap<Integer, ScheduledExecutorService> threadMap = new HashMap<Integer, ScheduledExecutorService>();

	public SendMailLogic(Remind remind) {
		this.remind = remind;
		service = Executors.newSingleThreadScheduledExecutor();
	}

	public void execute() { //リマインド設定を行う

		Runnable task1 = () -> { //リマインド時刻にメールを送信
			try {
				Properties property = new Properties();
				// 各種設定             property.put("mail.smtp.host", "smtp.gmail.com");
				property.put("mail.smtp.auth", "true");
				property.put("mail.smtp.starttls.enable", "true");
				property.put("mail.smtp.host", "smtp.gmail.com");
				property.put("mail.smtp.port", "587"
						+ "");
				property.put("mail.smtp.debug", "true");
				ResourceBundle bundle = ResourceBundle.getBundle("properties.mail");//リマインド配信元の情報を取得

				Session session = Session.getInstance(property, new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(bundle.getString("mail"), bundle.getString("pass"));
					}
				});

				MimeMessage mimeMessage = new MimeMessage(session);
				InternetAddress toAddress = new InternetAddress(remind.getMail()); //送信先
				mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);
				InternetAddress fromAddress = new InternetAddress(bundle.getString("mail")); //送信元
				mimeMessage.setFrom(fromAddress);
				mimeMessage.setSubject(remind.getText(), "ISO-2022-JP"); //タイトル
				mimeMessage.setText(remind.getText(), "ISO-2022-JP"); //本文
				mimeMessage.setText("--Sent with reminder App--", "ISO-2022-JP");
				Transport.send(mimeMessage); //メール送信
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		};
		Timestamp now = new Timestamp(System.currentTimeMillis()); //現在時刻
		long time = remind.getRemindTime().getTime() - now.getTime(); //リマインド時刻と現在時刻の差分
		sf = service.schedule(task1, time, TimeUnit.MILLISECONDS); //リマインドを設定

		futureMap.put(remind.getReimindId(), sf); //キャンセルを行う時のための格納
		threadMap.put(remind.getReimindId(), service);

	}
}