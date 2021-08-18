package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.MealGenre;

public class MealGenreDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ArrayList<MealGenre> selectMealGenreAll() throws SQLException {
		ArrayList<MealGenre> list = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM MEALGENRE";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int mealGenreId = rs.getInt("MEALGENREID");
				String mealGenreName = rs.getString("MEALGENRENAME");
				MealGenre mealGenre = new MealGenre(mealGenreId, mealGenreName);
				list.add(mealGenre);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
