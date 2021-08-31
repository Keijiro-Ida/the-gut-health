package model;

import java.util.concurrent.TimeUnit;

public class CancelMailLogic {
	public void execute(int remindId) throws InterruptedException {
		if (SendMailLogic.futureMap.get(remindId) != null) { //リマインド通知のキャンセル
			SendMailLogic.futureMap.get(remindId).cancel(true);//スレッドのキャンセル
			SendMailLogic.futureMap.remove(remindId);
			SendMailLogic.threadMap.get(remindId).shutdown();//スレッドの閉鎖
			SendMailLogic.threadMap.get(remindId).awaitTermination(1, TimeUnit.NANOSECONDS);
			SendMailLogic.threadMap.remove(remindId);

		}
	}
}
