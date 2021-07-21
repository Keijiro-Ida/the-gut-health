package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.MealAct;
import model.PostMealAct;

public class MealActDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");
	//データベースの情報を取得

	public MealAct mealActCreate(PostMealAct postMealAct) {
		MealAct mealAct = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL"),
				bundle.getString("DB_USER"),
				bundle.getString("DB_PASS"))) {

			String sql = "INSERT INTO MEALACT(USRID,ACTTIME,ACTDETAILID,ACTTEXT) VALUES(?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postMealAct.getUsrId());
			pstmt.setTimestamp(2, postMealAct.getActTime());
			pstmt.setInt(3, postMealAct.getActDetailId());
			pstmt.setString(4, postMealAct.getActText());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				int actId = rs.getInt(1);

				mealAct = new MealAct(postMealAct.getUsrId(), actId, postMealAct.getActTime(),
						postMealAct.getActDetailId(), postMealAct.getActText());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mealAct;
	}

}
