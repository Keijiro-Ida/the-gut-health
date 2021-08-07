package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
			String sql = "INSERT INTO PLANANDRESULT(USRID, PLANANDRESULTDATE)"
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
			String sql = "SELECT * FROM PLANANDRESULT WHERE USRID = ? AND PLANANDRESULTDATE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, users.getUsrId());
			Date today = new Date(System.currentTimeMillis());
			pstmt.setDate(2, today);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdAMSnackPlan = rs.getInt("ACTID_AM_SNACK_PLAN");
				int actIdAMSnack = rs.getInt("ACTID_AM_SNACK");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdPMSnackPlan = rs.getInt("ACTID_PM_SNACK_PLAN");
				int actIdPMSnack = rs.getInt("ACTID_PM_SNACK");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdNightSnackPlan = rs.getInt("ACTID_NIGHT_SNACK_PLAN");
				int actIdNightSnack = rs.getInt("ACTID_NIGHT_SNACK");
				int score = rs.getInt("SCORE");
				int scorePlan = rs.getInt("SCORE_PLAN");
				boolean isCommitted = rs.getBoolean("ISCOMMITTED");

				planAndResult = new PlanAndResult(users.getUsrId(), planAndResultId, today.toLocalDate(),
						actIdBreakfastPlan, actIdBreakfast,
						actIdAMSnackPlan, actIdAMSnack,
						actIdLunchPlan, actIdLunch,
						actIdPMSnackPlan, actIdPMSnack,
						actIdDinnerPlan, actIdDinner,
						actIdNightSnackPlan, actIdNightSnack,
						scorePlan, score, isCommitted);

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
			String sql = "SELECT * FROM PLANANDRESULT WHERE USRID = ? AND PLANANDRESULTDATE = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postPlanAndResult.getUsrId());
			;
			pstmt.setDate(2, Date.valueOf(postPlanAndResult.getDate()));

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdAMSnackPlan = rs.getInt("ACTID_AM_SNACK_PLAN");
				int actIdAMSnack = rs.getInt("ACTID_AM_SNACK");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdPMSnackPlan = rs.getInt("ACTID_PM_SNACK_PLAN");
				int actIdPMSnack = rs.getInt("ACTID_PM_SNACK");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdNightSnackPlan = rs.getInt("ACTID_NIGHT_SNACK_PLAN");
				int actIdNightSnack = rs.getInt("ACTID_NIGHT_SNACK");
				int score = rs.getInt("SCORE");
				int scorePlan = rs.getInt("SCORE_PLAN");
				boolean isCommitted = rs.getBoolean("ISCOMMITTED");

				planAndResult = new PlanAndResult(postPlanAndResult.getUsrId(), planAndResultId,
						postPlanAndResult.getDate(),
						actIdBreakfastPlan, actIdBreakfast,
						actIdAMSnackPlan, actIdAMSnack,
						actIdLunchPlan, actIdLunch,
						actIdPMSnackPlan, actIdPMSnack,
						actIdDinnerPlan, actIdDinner,
						actIdNightSnackPlan, actIdNightSnack,
						scorePlan, score, isCommitted);

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
			String sql = "UPDATE PLANANDRESULT SET ACTID_BREAKFAST_PLAN = ?, ACTID_BREAKFAST = ?, ACTID_AM_SNACK_PLAN = ?, ACTID_AM_SNACK = ?,"
					+ "ACTID_LUNCH_PLAN = ?, ACTID_LUNCH = ?, ACTID_PM_SNACK_PLAN = ?, ACTID_PM_SNACK = ?, ACTID_DINNER_PLAN = ?, ACTID_DINNER = ? "
					+ ", ACTID_NIGHT_SNACK_PLAN = ?, ACTID_NIGHT_SNACK = ?, SCORE_PLAN = ?, SCORE = ?, ISCOMMITTED = ? WHERE PLANANDRESULTID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, planAndResult.getActIdBreakfastPlan());
			pstmt.setInt(2, planAndResult.getActIdBreakfast());
			pstmt.setInt(3, planAndResult.getActIdAMSnackPlan());
			pstmt.setInt(4, planAndResult.getActIdAMSnack());
			pstmt.setInt(5, planAndResult.getActIdLunchPlan());
			pstmt.setInt(6, planAndResult.getActIdLunch());
			pstmt.setInt(7, planAndResult.getActIdPMSnackPlan());
			pstmt.setInt(8, planAndResult.getActIdPMSnack());
			pstmt.setInt(9, planAndResult.getActIdDinnerPlan());
			pstmt.setInt(10, planAndResult.getActIdDinner());
			pstmt.setInt(11, planAndResult.getActIdNightSnackPlan());
			pstmt.setInt(12, planAndResult.getActIdNightSnack());
			pstmt.setInt(13, planAndResult.getScorePlan());
			pstmt.setInt(14, planAndResult.getScore());
			pstmt.setBoolean(15, planAndResult.getIsCommitted());
			pstmt.setInt(16, planAndResult.getPlanAndResultId());

			result = pstmt.executeUpdate();

			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public ArrayList<PlanAndResult> getMonthPlanAndResultList(Users users, LocalDate localDate) {
		ArrayList<PlanAndResult> monthPlanAndResultList = new ArrayList<PlanAndResult>();
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM PLANANDRESULT WHERE USRID = ? AND PLANANDRESULTDATE BETWEEN ? AND ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, users.getUsrId());
			pstmt.setDate(2, Date.valueOf(localDate.withDayOfMonth(1)));
			pstmt.setDate(3,
					Date.valueOf(localDate.withDayOfMonth(1).plusMonths(1).minusDays(1)));
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int planAndResultId = rs.getInt("PLANANDRESULTID");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdAMSnackPlan = rs.getInt("ACTID_AM_SNACK_PLAN");
				int actIdAMSnack = rs.getInt("ACTID_AM_SNACK");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdPMSnackPlan = rs.getInt("ACTID_PM_SNACK_PLAN");
				int actIdPMSnack = rs.getInt("ACTID_PM_SNACK");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdNightSnackPlan = rs.getInt("ACTID_NIGHT_SNACK_PLAN");
				int actIdNightSnack = rs.getInt("ACTID_NIGHT_SNACK");
				int score = rs.getInt("SCORE");
				int scorePlan = rs.getInt("SCORE_PLAN");
				boolean isCommitted = rs.getBoolean("ISCOMMITTED");
				LocalDate planAndResultDate = rs.getDate("PLANANDRESULTDATE").toLocalDate();

				PlanAndResult planAndResult = new PlanAndResult(users.getUsrId(), planAndResultId,
						planAndResultDate,
						actIdBreakfastPlan, actIdBreakfast,
						actIdAMSnackPlan, actIdAMSnack,
						actIdLunchPlan, actIdLunch,
						actIdPMSnackPlan, actIdPMSnack,
						actIdDinnerPlan, actIdDinner,
						actIdNightSnackPlan, actIdNightSnack,
						scorePlan, score, isCommitted);

				monthPlanAndResultList.add(planAndResult);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return monthPlanAndResultList;
	}

	public PlanAndResult findByPlanAndResultId(int planAndResultId) {
		PlanAndResult planAndResult = null;
		try (Connection conn = DriverManager.getConnection(
				bundle.getString("JDBC_URL_LOCAL"),
				bundle.getString("DB_USER_LOCAL"),
				bundle.getString("DB_PASS_LOCAL"))) {
			String sql = "SELECT * FROM PLANANDRESULT WHERE PLANANDRESULTID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, planAndResultId);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				int usrId = rs.getInt("USRID");
				int actIdBreakfastPlan = rs.getInt("ACTID_BREAKFAST_PLAN");
				int actIdBreakfast = rs.getInt("ACTID_BREAKFAST");
				int actIdAMSnackPlan = rs.getInt("ACTID_AM_SNACK_PLAN");
				int actIdAMSnack = rs.getInt("ACTID_AM_SNACK");
				int actIdLunchPlan = rs.getInt("ACTID_LUNCH_PLAN");
				int actIdLunch = rs.getInt("ACTID_LUNCH");
				int actIdPMSnackPlan = rs.getInt("ACTID_PM_SNACK_PLAN");
				int actIdPMSnack = rs.getInt("ACTID_PM_SNACK");
				int actIdDinnerPlan = rs.getInt("ACTID_DINNER_PLAN");
				int actIdDinner = rs.getInt("ACTID_DINNER");
				int actIdNightSnackPlan = rs.getInt("ACTID_NIGHT_SNACK_PLAN");
				int actIdNightSnack = rs.getInt("ACTID_NIGHT_SNACK");
				int score = rs.getInt("SCORE");
				int scorePlan = rs.getInt("SCORE_PLAN");
				boolean isCommitted = rs.getBoolean("ISCOMMITTED");
				LocalDate planAndResultDate = rs.getDate("PLANANDRESULTDATE").toLocalDate();

				planAndResult = new PlanAndResult(usrId, planAndResultId,
						planAndResultDate,
						actIdBreakfastPlan, actIdBreakfast,
						actIdAMSnackPlan, actIdAMSnack,
						actIdLunchPlan, actIdLunch,
						actIdPMSnackPlan, actIdPMSnack,
						actIdDinnerPlan, actIdDinner,
						actIdNightSnackPlan, actIdNightSnack,
						scorePlan, score, isCommitted);

			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return planAndResult;
	}

}
