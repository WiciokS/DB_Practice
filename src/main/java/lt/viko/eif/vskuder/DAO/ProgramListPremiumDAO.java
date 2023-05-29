package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.PremiumUserProgramAssociation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramListPremiumDAO extends DAO{
    public static List<PremiumUserProgramAssociation> getPremiumUserProgramAssociations(int userId){
        List<PremiumUserProgramAssociation> associations = new ArrayList<>();
        String sql = "SELECT * FROM ProgramList_Premium WHERE UserID = " + userId;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PremiumUserProgramAssociation o = new PremiumUserProgramAssociation();
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                associations.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associations;
    }

    public static PremiumUserProgramAssociation createPremiumUserProgramAssociation(PremiumUserProgramAssociation premiumUserProgramAssociation) {
        String sql = "INSERT INTO ProgramList_Premium (UserID, ProgramID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, premiumUserProgramAssociation.getUserID().getUserID());
            stmt.setInt(2, premiumUserProgramAssociation.getProgramID().getProgramID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating premium user program association failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return premiumUserProgramAssociation;
    }

    public static boolean updatePremiumUserProgramAssociation(PremiumUserProgramAssociation premiumUserProgramAssociation) {
        String sql = "UPDATE ProgramList_Premium SET UserID = ?, ProgramID = ? WHERE UserID = ? AND ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, premiumUserProgramAssociation.getUserID().getUserID());
            stmt.setInt(2, premiumUserProgramAssociation.getProgramID().getProgramID());
            stmt.setInt(3, premiumUserProgramAssociation.getUserID().getUserID());
            stmt.setInt(4, premiumUserProgramAssociation.getProgramID().getProgramID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePremiumUserProgramAssociation(int userID, int programID) {
        String sql = "DELETE FROM ProgramList_Premium WHERE UserID = ? AND ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userID);
            stmt.setInt(2, programID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
