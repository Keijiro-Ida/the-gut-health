package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.ActDate;
import model.PostActDate;

public class ActDateDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public ActDate actDateCreate(PostActDate postActDate) {
		ActDate actDate = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
				bundle.getString("DB_USER"),
				bundle.getString("DB_PASS"))) {
			String sql = "INSERT INTO ACTDATE(USRID, ACTDATE)"
					+ "		VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postActDate.getUsrId());
			pstmt.setDate(2, new java.sql.Date(postActDate.getActDate().getTime()));

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				int actDateId = rs.getInt(1);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return actDate;
	}

}
