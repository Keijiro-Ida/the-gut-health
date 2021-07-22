package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.ActDate;
import model.PostActDate;
import model.users.Users;

public class ActDateDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ActDate actDateCreate(PostActDate postActDate) {
		ActDate actDate = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "INSERT INTO ACTDATE(USRID, ACTDATETODAY)"
					+ "		VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postActDate.getUsrId());
			pstmt.setDate(2, postActDate.getActDateToday());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				int actDateId = rs.getInt(1);
				actDate = new ActDate(postActDate.getUsrId(), actDateId, postActDate.getActDateToday());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actDate;
	}

	public ActDate findByUsers(Users users) {
		ActDate actDate = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM ACTDATE WHERE USRID = ? AND ACTDATETODAY = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, users.getUsrId());
			Date today = new Date(System.currentTimeMillis());
			pstmt.setDate(2, today);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int dateId = rs.getInt("ACTDATEID");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdSnack = rs.getInt("ACTID_SNACK");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdSnackPlan = rs.getInt("ACTID_SNACK_PLAN");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int score = rs.getInt("SCORE");

				actDate = new ActDate(users.getUsrId(), dateId, today, actIdBreakfast, actIdLunch, actIdSnack,
						actIdDinner, actIdBreakfastPlan, actIdLunchPlan, actIdSnackPlan, actIdDinnerPlan, score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actDate;
	}
	/*
	public int updateActDate(ActDate actDate) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "UPDATE ACTDATE SET ACTID_BREAKFAST = 1 AND ACTID_LUNCH = 2"
					+ "AND ACTID_SNACK = 3 AND ACTID_DINNER = 4 AND ACTID_BREAKFAST_PLAN = 5"
					+ "AND ACTID_LUNCH_PLAN = 6 AND ACTID_SNACK_PLAN = 7 AND ACTID_DINNER_PLAN = 8 WHERE ACTDATEID = 1";
			PreparedStatement pstmt = conn.prepareStatement(sql);
	
			pstmt.setInt(1, actDate.getActIdBreakfast());
			System.out.println("actDate.getActIdBreakfast()" + actDate.getActIdBreakfast());
			pstmt.setInt(2, actDate.getActIdLunch());
			System.out.println("actDate.getActIdLunch()" + actDate.getActIdLunch());
			pstmt.setInt(3, actDate.getActIdSnack());
			pstmt.setInt(4, actDate.getActIdDinner());
			pstmt.setInt(5, actDate.getActIdBreakfastPlan());
			pstmt.setInt(6, actDate.getActIdLunchPlan());
			pstmt.setInt(7, actDate.getActIdSnackPlan());
			pstmt.setInt(8, actDate.getActIdDinnerPlan());
			pstmt.setInt(1, 1);
	
			result = pstmt.executeUpdate();
			System.out.println("result:" + result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}*/

}
