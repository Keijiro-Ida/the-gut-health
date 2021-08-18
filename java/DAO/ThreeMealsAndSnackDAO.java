package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ThreeMealsAndSnackDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ArrayList<String> selectThreeMeals() throws SQLException {
		ArrayList<String> list = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM THREEMEALSANDSNACK";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String threeMealsName = rs.getString("THREEMEALSNAME");
				list.add(threeMealsName);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
}
