package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.PostRemind;
import model.Remind;

public class RemindDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public Remind createRemind(PostRemind postRemind) throws SQLException {
		Remind remind = null;

		try (Connection con = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "INSERT INTO REMIND(ACTID, REMINDTIME, TEXT, MAIL) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postRemind.getMealActId());
			pstmt.setTimestamp(2, postRemind.getRemindTime());
			pstmt.setString(3, postRemind.getText());
			pstmt.setString(4, postRemind.getMail());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int remindId = rs.getInt(1);

				remind = new Remind(postRemind.getMealActId(), remindId, postRemind.getRemindTime(),
						postRemind.getText(), postRemind.getMail());
			}

		} catch (SQLException e) {
			throw e;
		}
		return remind;
	}

	public int deleteByActId(int actId) throws SQLException {

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "DELETE FROM REMIND WHERE ACTID  = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actId);

			int result = pstmt.executeUpdate();
			return result;

		} catch (SQLException e) {
			throw e;
		}
	}

	public int selectRemindIdByActId(int actId) throws SQLException {
		int remindId = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM REMIND WHERE ACTID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, actId);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {

				remindId = rs.getInt("REMINDID");

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return remindId;
	}

	public ArrayList<Remind> findAll(Timestamp now) {
		ArrayList<Remind> list = new ArrayList<Remind>();
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM REMIND WHERE REMINDTIME > ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setTimestamp(1, now);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int actId = rs.getInt("ACTID");
				int remindId = rs.getInt("REMINDID");
				Timestamp remindTime = rs.getTimestamp("REMINDTIME");
				String text = rs.getString("TEXT");
				String mail = rs.getString("MAIL");

				Remind remind = new Remind(actId, remindId, remindTime, text, mail);
				list.add(remind);
				System.out.println("ファイル取得RemindId" + remindId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return list;
		}
		return list;
	}
}
