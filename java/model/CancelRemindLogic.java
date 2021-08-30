package model;

import java.sql.SQLException;

import DAO.RemindDAO;

public class CancelRemindLogic {
	//リマインド通知のキャンセル及び削除
	public void execute(int actId) throws SQLException, InterruptedException {
		if (actId != 0) {
			RemindDAO remindDAO = new RemindDAO();
			int remindId = remindDAO.selectRemindIdByActId(actId);
			System.out.println("CancelRemindLogic:remindId:" + remindId);

			//メール送信のスレッドのキャンセル
			CancelMailLogic cancelBO = new CancelMailLogic();
			cancelBO.execute(remindId);
			remindDAO.deleteByActId(actId); //データベースのリマインドを削除
		}
	}

}
