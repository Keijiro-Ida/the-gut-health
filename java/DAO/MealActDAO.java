package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import model.MealAct;
import model.PostMealAct;

public class MealActDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
	//データベースの情報を取得

	public MealAct mealActCreate(PostMealAct postMealAct) {
		MealAct mealAct = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "INSERT INTO MEALACT(USRID, PLANANDRESULTID, ACTTIME, MEALID, MEALTYPEID) VALUES(?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postMealAct.getUsrId());
			pstmt.setInt(2, postMealAct.getPlanAndResultId());
			pstmt.setTimestamp(3, Timestamp.valueOf(postMealAct.getActTime()));
			pstmt.setInt(4, postMealAct.getMealId());
			pstmt.setInt(5, postMealAct.getMealTypeId());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int actId = rs.getInt(1);

				mealAct = new MealAct(postMealAct.getUsrId(), postMealAct.getPlanAndResultId(), actId,
						postMealAct.getActTime(),
						postMealAct.getMealId(), postMealAct.getMealTypeId());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mealAct;
	}

	public MealAct selectByActId(int actId) {
		MealAct mealAct = null;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM MEALACT WHERE ACTID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int usrId = rs.getInt("USRID");
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				LocalDateTime actTime = rs.getTimestamp("ACTTIME").toLocalDateTime();
				int mealId = rs.getInt("MealId");
				int mealTypeId = rs.getInt("MealTypeId");

				mealAct = new MealAct(usrId, planAndResultId, actId, actTime, mealId, mealTypeId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mealAct;
	}
}
