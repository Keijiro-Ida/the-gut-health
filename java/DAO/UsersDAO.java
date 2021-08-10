package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.users.Login;
import model.users.SignUp;
import model.users.Users;

public class UsersDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
	//データベースの情報を取得

	public Users findByLogin(Login login) {
		Users users = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT USRID, MAIL, PASS FROM USERS WHERE MAIL = ? AND PASS = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getMail());
			pstmt.setString(2, login.getPass());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int usrId = rs.getInt("USRID");
				String mail = rs.getString("MAIL");
				String pass = rs.getString("PASS");
				users = new Users(usrId, mail, pass);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return users;
	}

	public int createUsers(SignUp signUp) {

		int result = 0;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "INSERT INTO USERS(MAIL, PASS) VALUES ( ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, signUp.getMail());
			pstmt.setString(2, signUp.getPass());

			result = pstmt.executeUpdate();

			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}

	}

	public int updateUsers(Users users) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "UPDATE USERS SET MAIL = ? , PASS = ?  WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, users.getMail());
			pstmt.setString(2, users.getPass());
			pstmt.setInt(3, users.getUsrId());
			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public Users findByUsrId(int usrId) {
		Users users = null;
		// TODO 自動生成されたメソッド・スタブ
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {

			String sql = "SELECT * FROM USERS WHERE USRID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, usrId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String mail = rs.getString("MAIL");
				String pass = rs.getString("PASS");
				users = new Users(usrId, mail, pass);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return users;

	}

}
