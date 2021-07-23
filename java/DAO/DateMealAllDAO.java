package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.DateMealAll;
import model.PostDate;
import model.users.Users;

public class DateMealAllDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public DateMealAll createDateMealAll(PostDate postDate) {
		DateMealAll dateMealAll = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "INSERT INTO DATEMEALALL(USRID, DATETODAY)"
					+ "		VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postDate.getUsrId());
			pstmt.setDate(2, postDate.getDateToday());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				int dateId = rs.getInt(1);
				dateMealAll = new DateMealAll(postDate.getUsrId(), dateId, postDate.getDateToday());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dateMealAll;
	}

	public DateMealAll findByUsers(Users users) {
		DateMealAll dateMealAll = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM DATEMEALALL WHERE USRID = ? AND DATETODAY = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, users.getUsrId());
			Date today = new Date(System.currentTimeMillis());
			pstmt.setDate(2, today);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int dateId = rs.getInt("DATEID");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdSnack = rs.getInt("ACTID_SNACK");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdSnackPlan = rs.getInt("ACTID_SNACK_PLAN");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int score = rs.getInt("SCORE");

				dateMealAll = new DateMealAll(users.getUsrId(), dateId, today, actIdBreakfast, actIdLunch, actIdSnack,
						actIdDinner, actIdBreakfastPlan, actIdLunchPlan, actIdSnackPlan, actIdDinnerPlan, score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dateMealAll;
	}

	public DateMealAll findByPostDate(PostDate postDate) {
		DateMealAll dateMealAll = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM DATEMEALALL WHERE USRID = ? AND DATETODAY = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDate.getUsrId());
			;
			pstmt.setDate(2, postDate.getDateToday());

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int dateId = rs.getInt("DATEID");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdSnack = rs.getInt("ACTID_SNACK");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdSnackPlan = rs.getInt("ACTID_SNACK_PLAN");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int score = rs.getInt("SCORE");

				dateMealAll = new DateMealAll(postDate.getUsrId(), dateId, postDate.getDateToday(), actIdBreakfast,
						actIdLunch, actIdSnack,
						actIdDinner, actIdBreakfastPlan, actIdLunchPlan, actIdSnackPlan, actIdDinnerPlan, score);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return dateMealAll;
	}

	public int updateDateMealAll(DateMealAll dateMealAll) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "UPDATE DATEMEALALL SET ACTID_BREAKFAST = ?, ACTID_LUNCH = ?,"
					+ "ACTID_SNACK = ?, ACTID_DINNER = ?, ACTID_BREAKFAST_PLAN = ?,"
					+ "ACTID_LUNCH_PLAN = ?, ACTID_SNACK_PLAN = ?, ACTID_DINNER_PLAN = ?, SCORE = ? WHERE DATEID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, dateMealAll.getActIdBreakfast());
			pstmt.setInt(2, dateMealAll.getActIdLunch());
			pstmt.setInt(3, dateMealAll.getActIdSnack());
			pstmt.setInt(4, dateMealAll.getActIdDinner());
			pstmt.setInt(5, dateMealAll.getActIdBreakfastPlan());
			pstmt.setInt(6, dateMealAll.getActIdLunchPlan());
			pstmt.setInt(7, dateMealAll.getActIdSnackPlan());
			pstmt.setInt(8, dateMealAll.getActIdDinnerPlan());
			pstmt.setInt(9, dateMealAll.getScore());
			pstmt.setInt(10, dateMealAll.getDateId());

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
