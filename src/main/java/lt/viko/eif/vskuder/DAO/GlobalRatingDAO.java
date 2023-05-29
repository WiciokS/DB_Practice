package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.GlobalRating;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GlobalRatingDAO extends DAO{
    public static GlobalRating getGlobalRating(int id){
        String sql = "Select *" +
                "from GlobalRatings" +
                "where ProgramID = " + id;

        GlobalRating o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new GlobalRating();
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                o.setRating(rs.getFloat("Rating"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static GlobalRating createGlobalRating(AIProgram program, Float rating){
        String sql = "INSERT INTO GlobalRatings (ProgramID, Rating) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, program.getProgramID());
            stmt.setFloat(2, rating);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                GlobalRating newGlobalRating = new GlobalRating();
                newGlobalRating.setProgramID(program);
                newGlobalRating.setRating(rating);
                return newGlobalRating;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteGlobalRating(int programId) {
        String sql = "DELETE FROM GlobalRatings WHERE ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, programId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateGlobalRating(GlobalRating globalRating) {
        String sql = "UPDATE GlobalRatings SET Rating = ? WHERE ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setFloat(1, globalRating.getRating());
            stmt.setInt(2, globalRating.getProgramID().getProgramID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<GlobalRating> getAllGlobalRatings() {
        List<GlobalRating> globalRatings = new ArrayList();
        String sql = "SELECT * FROM GlobalRatings";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GlobalRating globalRating = new GlobalRating();
                AIProgram program = AIProgramDAO.getAIProgram(rs.getInt("ProgramID"));
                globalRating.setProgramID(program);
                globalRating.setRating(rs.getFloat("Rating"));
                globalRatings.add(globalRating);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return globalRatings;
    }
}
