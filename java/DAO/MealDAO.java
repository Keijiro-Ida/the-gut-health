package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.Meal;

public class MealDAO {

	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ArrayList<Meal> selectMealByMealGenre(int mealGenreId) {
		ArrayList<Meal> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM MEAL WHERE MEALGENREID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mealGenreId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int mealId = rs.getInt("MEALID");
				String mealName = rs.getString("MEALNAME");
				int foodId = rs.getInt("FOODID");

				Meal meal = new Meal(mealId, mealName, foodId, mealGenreId);
				list.add(meal);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public Meal selectMealByMealId(int mealId) {
		Meal meal = null;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM MEAL WHERE MEALID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mealId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				String mealName = rs.getString("MEALNAME");
				int foodId = rs.getInt("FOODID");
				int mealGenreId = rs.getInt("MEALGENREID");

				meal = new Meal(mealId, mealName, foodId, mealGenreId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return meal;
	}
}
