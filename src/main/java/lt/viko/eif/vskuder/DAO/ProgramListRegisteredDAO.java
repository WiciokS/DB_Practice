package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.RegisteredUserProgramAssociation;
import lt.viko.eif.vskuder.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProgramListRegisteredDAO extends DAO{
    public final String database_name = "ProgramList_Registered";
    public static List<RegisteredUserProgramAssociation> getProgramListRegistered(int userId) {
        List<RegisteredUserProgramAssociation> associations = new ArrayList<>();
        String sql = "SELECT * FROM ProgramList_Registered WHERE UserID = " + userId;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RegisteredUserProgramAssociation o = new RegisteredUserProgramAssociation();
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                associations.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associations;
    }

    public static List<RegisteredUserProgramAssociation> getProgramListRegistered() {
        List<RegisteredUserProgramAssociation> associations = new ArrayList<>();
        String sql = "SELECT * FROM ProgramList_Registered";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RegisteredUserProgramAssociation o = new RegisteredUserProgramAssociation();
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                associations.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associations;
    }

    public static RegisteredUserProgramAssociation createRegisteredUserProgramAssociation(User user, AIProgram aiProgram) {
        String sql = "INSERT INTO ProgramList_Registered (UserID, ProgramID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, DAO.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, user.getUserID());
            stmt.setInt(2, aiProgram.getProgramID());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                RegisteredUserProgramAssociation newUserProgram = new RegisteredUserProgramAssociation();
                newUserProgram.setUserID(user);
                newUserProgram.setProgramID(aiProgram);
                return newUserProgram;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean updateRegisteredUserProgramAssociation(RegisteredUserProgramAssociation registeredUserProgramAssociation) {
        String sql = "UPDATE ProgramList_Registered SET UserID = ?, ProgramID = ? WHERE UserID = ? AND ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, registeredUserProgramAssociation.getUserID().getUserID());
            stmt.setInt(2, registeredUserProgramAssociation.getProgramID().getProgramID());
            stmt.setInt(3, registeredUserProgramAssociation.getUserID().getUserID());
            stmt.setInt(4, registeredUserProgramAssociation.getProgramID().getProgramID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRegisteredUserProgramAssociation(int userID, int programID) {
        String sql = "DELETE FROM ProgramList_Registered WHERE UserID = ? AND ProgramID = ?";

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
