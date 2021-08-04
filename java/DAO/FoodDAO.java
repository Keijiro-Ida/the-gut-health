package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FoodDAO {
	public int selectDigestionMinutesFromId(int foodId) {
		ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
		int digestionMinutes = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT DIGESTIONMINUTES_INT FROM FOOD WHERE FOODID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, foodId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				digestionMinutes = rs.getInt("DIGESTIONMINUTES_INT");

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return digestionMinutes;
	}

}
