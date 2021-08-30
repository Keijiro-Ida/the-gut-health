package model;

import java.sql.SQLException;

import DAO.RemindDAO;

public class PostRemindLogic {

	public Remind execute(PostRemind postRemind) throws SQLException {
		RemindDAO dao = new RemindDAO();

		Remind remind = dao.createRemind(postRemind);
		return remind;
	}

}
