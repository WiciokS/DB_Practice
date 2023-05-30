package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgramReview;
import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AIProgramReviewDAO extends DAO{
    public final String database_name = "AIProgramReviews";
    public static AIProgramReview getAIProgramReview(int id){
        String sql = "Select *" +
                "from AIProgramReviews" +
                "where ReviewID = " + id;

        AIProgramReview o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new AIProgramReview();
                o.setReviewID(rs.getInt("ReviewID"));
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setReview(rs.getString("Review"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static AIProgramReview createAIProgramReview(AIProgramReview aiProgramReview) {
        String sql = "INSERT INTO AIProgramReviews (ProgramID, UserID, Review) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, aiProgramReview.getProgramID().getProgramID());
            stmt.setInt(2, aiProgramReview.getUserID().getUserID());
            stmt.setString(3, aiProgramReview.getReview());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating AIProgramReview failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    aiProgramReview.setReviewID((int) generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating AIProgramReview failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return aiProgramReview;
    }

    public static List<AIProgramReview> getAllAIProgramReviews() {
        List<AIProgramReview> aiProgramReviews = new ArrayList<>();
        String sql = "SELECT * FROM AIProgramReviews";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AIProgramReview aiProgramReview = new AIProgramReview();
                AIProgram program = new AIProgram();
                User user = new User();

                program.setProgramID(rs.getInt("ProgramID"));
                user.setUserID(rs.getInt("UserID"));

                aiProgramReview.setReviewID(rs.getInt("ReviewID"));
                aiProgramReview.setProgramID(program);
                aiProgramReview.setUserID(user);
                aiProgramReview.setReview(rs.getString("Review"));

                aiProgramReviews.add(aiProgramReview);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aiProgramReviews;
    }

    public static boolean updateAIProgramReview(AIProgramReview aiProgramReview) {
        String sql = "UPDATE AIProgramReviews SET ProgramID = ?, UserID = ?, Review = ? WHERE ReviewID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aiProgramReview.getProgramID().getProgramID());
            stmt.setInt(2, aiProgramReview.getUserID().getUserID());
            stmt.setString(3, aiProgramReview.getReview());
            stmt.setInt(4, aiProgramReview.getReviewID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAIProgramReview(int reviewID) {
        String sql = "DELETE FROM AIProgramReviews WHERE ReviewID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reviewID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
