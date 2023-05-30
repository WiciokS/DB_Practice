package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.PremiumUserProgramAssociation;
import lt.viko.eif.vskuder.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramListPremiumDAO extends DAO{
    public final String database_name = "ProgramList_Premium";

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

    public static List<PremiumUserProgramAssociation> getPremiumUserProgramAssociations(){
        List<PremiumUserProgramAssociation> associations = new ArrayList<>();
        String sql = "SELECT * FROM ProgramList_Premium";

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

    public static PremiumUserProgramAssociation createPremiumUserProgramAssociation(User user, AIProgram aiProgram) {
        String sql = "INSERT INTO ProgramList_Premium (UserID, ProgramID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, DAO.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, user.getUserID());
            stmt.setInt(2, aiProgram.getProgramID());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                PremiumUserProgramAssociation newUserProgram = new PremiumUserProgramAssociation();
                newUserProgram.setUserID(user);
                newUserProgram.setProgramID(aiProgram);
                return newUserProgram;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
