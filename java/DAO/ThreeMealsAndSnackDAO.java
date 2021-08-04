package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.ThreeMealsAndSnack;

public class ThreeMealsAndSnackDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ArrayList<ThreeMealsAndSnack> selectThreeMeals() {
		ArrayList<ThreeMealsAndSnack> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM THREEMEALSANDSNACK";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int threeMealsId = rs.getInt("THREEMEALSID");
				String threeMealsName = rs.getString("THREEMEALSNAME");

				ThreeMealsAndSnack threeMealsAndSnack = new ThreeMealsAndSnack(threeMealsId, threeMealsName);
				list.add(threeMealsAndSnack);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}
}
