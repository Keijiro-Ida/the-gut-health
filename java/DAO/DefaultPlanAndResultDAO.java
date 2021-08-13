package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.DefaultPlanAndResult;
import model.PostDefaultPlanAndResult;

public class DefaultPlanAndResultDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public int createDefaultPlanAndResult(PostDefaultPlanAndResult postDefault) {
		int result = 0;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "INSERT INTO DEFAULTPLANANDRESULT(USRID, BREAKFASTID, BREAKFASTTIME, AMSNACKID, AMSNACKTIME, LUNCHID, LUNCHTIME, PMSNACKID, PMSNACKTIME, DINNERID, DINNERTIME, NIGHTSNACKID, NIGHTSNACKTIME)"
					+ "		VALUES(?, ?, ?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postDefault.getUsrId());
			pstmt.setInt(2, postDefault.getBreakfastId());
			pstmt.setString(3, postDefault.getBreakfastTime());
			pstmt.setInt(4, postDefault.getAmSnackId());
			pstmt.setString(5, postDefault.getAmSnackTime());
			pstmt.setInt(6, postDefault.getLunchId());
			pstmt.setString(7, postDefault.getLunchTime());
			pstmt.setInt(8, postDefault.getPmSnackId());
			pstmt.setString(9, postDefault.getPmSnackTime());
			pstmt.setInt(10, postDefault.getDinnerId());
			pstmt.setString(11, postDefault.getDinnerTime());
			pstmt.setInt(12, postDefault.getNightSnackId());
			pstmt.setString(13, postDefault.getNightSnackTime());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	public DefaultPlanAndResult findByUsrId(int usrId) {
		DefaultPlanAndResult defaultSetting = null;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM DEFAULTPLANANDRESULT WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, usrId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int breakfastId = rs.getInt("BREAKFASTID");
				int lunchId = rs.getInt("LUNCHID");
				int amSnackId = rs.getInt("AMSNACKID");
				int pmSnackId = rs.getInt("PMSNACKID");
				int dinnerId = rs.getInt("DINNERID");
				int nightSnackId = rs.getInt("NIGHTSNACKID");
				String breakfastTime = rs.getString("BREAKFASTTIME");
				String amSnackTime = rs.getString("AMSNACKTIME");
				String lunchTime = rs.getString("LUNCHTIME");
				String pmSnackTime = rs.getString("PMSNACKTIME");
				String dinnerTime = rs.getString("DINNERTIME");
				String nightSnackTime = rs.getString("NIGHTSNACKTIME");
				int defaultId = rs.getInt("DEFAULTID");

				defaultSetting = new DefaultPlanAndResult(usrId, defaultId, breakfastId, breakfastTime, amSnackId,
						amSnackTime, lunchId, lunchTime, pmSnackId, pmSnackTime, dinnerId, dinnerTime, nightSnackId,
						nightSnackTime);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return defaultSetting;
	}

	public int deleteByUsrId(int usrId) {

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "DELETE FROM DEFAULTPLANANDRESULT WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, usrId);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
