package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import model.PlanAndResult;
import model.PostPlanAndResult;
import model.users.Users;

public class PlanAndResultDAO {
	ResourceBundle bundle = ResourceBundle.getBundle("properties.database");

	public PlanAndResult createPlanAndResult(PostPlanAndResult postPlanAndResult) {
		PlanAndResult planAndResult = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "INSERT INTO PLANANDRESULT(USRID, DATE)"
					+ "		VALUES(?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, postPlanAndResult.getUsrId());
			pstmt.setDate(2, Date.valueOf(postPlanAndResult.getDate()));

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();

			if (rs.next()) {
				int planAndResultId = rs.getInt(1);
				planAndResult = new PlanAndResult(postPlanAndResult.getUsrId(), planAndResultId,
						postPlanAndResult.getDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return planAndResult;
	}

	public PlanAndResult findByUsers(Users users) {
		PlanAndResult planAndResult = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM PLANANDRESULT WHERE USRID = ? AND DATE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, users.getUsrId());
			Date today = new Date(System.currentTimeMillis());
			pstmt.setDate(2, today);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdSnack = rs.getInt("ACTID_SNACK");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int score = rs.getInt("SCORE");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdSnackPlan = rs.getInt("ACTID_SNACK_PLAN");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int scorePlan = rs.getInt("SCORE_PLAN");

				planAndResult = new PlanAndResult(users.getUsrId(), planAndResultId, LocalDate.now(), actIdBreakfast,
						actIdLunch,
						actIdSnack,
						actIdDinner, score, actIdBreakfastPlan, actIdLunchPlan, actIdSnackPlan, actIdDinnerPlan,
						scorePlan);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return planAndResult;
	}

	public PlanAndResult findByPostPlanAndResult(PostPlanAndResult postPlanAndResult) {
		PlanAndResult planAndResult = null;

		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM PLANANDRESULT WHERE USRID = ? AND DATE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postPlanAndResult.getUsrId());
			;
			pstmt.setDate(2, Date.valueOf(postPlanAndResult.getDate()));

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdSnack = rs.getInt("ACTID_SNACK");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int score = rs.getInt("SCORE");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdSnackPlan = rs.getInt("ACTID_SNACK_PLAN");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int scorePlan = rs.getInt("SCORE_PLAN");

				planAndResult = new PlanAndResult(postPlanAndResult.getUsrId(), planAndResultId, LocalDate.now(),
						actIdBreakfast,
						actIdLunch, actIdSnack,
						actIdDinner, score, actIdBreakfastPlan, actIdLunchPlan, actIdSnackPlan, actIdDinnerPlan,
						scorePlan);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return planAndResult;
	}

	public int updatePlanAndResult(PlanAndResult planAndResult) {
		int result = 0;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "UPDATE PLANANDRESULT SET ACTID_BREAKFAST = ?, ACTID_LUNCH = ?,"
					+ "ACTID_SNACK = ?, ACTID_DINNER = ?, SCORE = ?, ACTID_BREAKFAST_PLAN = ?,"
					+ "ACTID_LUNCH_PLAN = ?, ACTID_SNACK_PLAN = ?, ACTID_DINNER_PLAN = ?, SCORE_PLAN = ? WHERE PLANANDRESULTID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, planAndResult.getActIdBreakfast());
			pstmt.setInt(2, planAndResult.getActIdLunch());
			pstmt.setInt(3, planAndResult.getActIdSnack());
			pstmt.setInt(4, planAndResult.getActIdDinner());
			pstmt.setInt(5, planAndResult.getScore());
			pstmt.setInt(6, planAndResult.getActIdBreakfastPlan());
			pstmt.setInt(7, planAndResult.getActIdLunchPlan());
			pstmt.setInt(8, planAndResult.getActIdSnackPlan());
			pstmt.setInt(9, planAndResult.getActIdDinnerPlan());
			pstmt.setInt(10, planAndResult.getScorePlan());
			pstmt.setInt(11, planAndResult.getPlanAndResultId());

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
