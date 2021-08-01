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

			String sql = "INSERT INTO MEALACT(PLANANDRESULTID, ACTTIME, MEALID, THREEMEALSID) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postMealAct.getPlanAndResultId());
			pstmt.setTimestamp(2, Timestamp.valueOf(postMealAct.getActTime()));
			pstmt.setInt(3, postMealAct.getMealId());
			pstmt.setInt(4, postMealAct.getMealTypeId());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int actId = rs.getInt(1);

				mealAct = new MealAct(postMealAct.getPlanAndResultId(), actId,
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
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				LocalDateTime actTime = rs.getTimestamp("ACTTIME").toLocalDateTime();
				int mealId = rs.getInt("MEALID");
				int threeMealsId = rs.getInt("THREEMEALSID");

				mealAct = new MealAct(planAndResultId, actId, actTime, mealId, threeMealsId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mealAct;
	}
}
