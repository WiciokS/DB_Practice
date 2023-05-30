package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.AIProgramRating;
import lt.viko.eif.vskuder.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AIProgramRatingDAO extends DAO{
    public String database_name = "AIProgramRatings";
    public static AIProgramRating getAIProgramRating(int id){
        String sql = "Select * " +
                "from AIProgramRatings " +
                "where RatingID = " + id;

        AIProgramRating o = null;;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new AIProgramRating();
                o.setRatingID(rs.getInt("RatingID"));
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setRating(rs.getInt("Rating"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public static AIProgramRating createAIProgramRating(AIProgramRating aiProgramRating) {
        String sql = "INSERT INTO AIProgramRatings (ProgramID, UserID, Rating) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, aiProgramRating.getProgramID().getProgramID());
            stmt.setInt(2, aiProgramRating.getUserID().getUserID());
            stmt.setInt(3, aiProgramRating.getRating());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating AIProgramRating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    aiProgramRating.setRatingID((int) generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating AIProgramRating failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return aiProgramRating;
    }

    public static List<AIProgramRating> getAllAIProgramRatings() {
        List<AIProgramRating> aiProgramRatings = new ArrayList<>();
        String sql = "SELECT * FROM AIProgramRatings";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                AIProgramRating aiProgramRating = new AIProgramRating();
                AIProgram program = new AIProgram();
                User user = new User();

                program.setProgramID(rs.getInt("ProgramID"));
                user.setUserID(rs.getInt("UserID"));

                aiProgramRating.setRatingID(rs.getInt("RatingID"));
                aiProgramRating.setProgramID(program);
                aiProgramRating.setUserID(user);
                aiProgramRating.setRating(rs.getInt("Rating"));

                aiProgramRatings.add(aiProgramRating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aiProgramRatings;
    }

    public static boolean updateAIProgramRating(AIProgramRating aiProgramRating) {
        String sql = "UPDATE AIProgramRatings SET ProgramID = ?, UserID = ?, Rating = ? WHERE RatingID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, aiProgramRating.getProgramID().getProgramID());
            stmt.setInt(2, aiProgramRating.getUserID().getUserID());
            stmt.setInt(3, aiProgramRating.getRating());
            stmt.setInt(4, aiProgramRating.getRatingID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAIProgramRating(int ratingID) {
        String sql = "DELETE FROM AIProgramRatings WHERE RatingID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ratingID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
