package lt.viko.eif.vskuder.DAO;

import java.util.Map;
import lt.viko.eif.vskuder.models.AuditTrail;
import lt.viko.eif.vskuder.models.Guest;
import lt.viko.eif.vskuder.models.User;

import java.sql.*;

public class AuditTrailDAO extends DAO{
    public final String database_name = "AuditTrail";
    public static AuditTrail getAuditTrail(int id) {
        String sql = "Select * " +
                "from AuditTrail " +
                "where AuditID = " + id;

        AuditTrail o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql , Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new AuditTrail();
                o.setAuditID(rs.getInt("AuditID"));
                Map.Entry<Guest, User> check = GeneralUserDAO.getGuestOrUser(id);
                if (check.getKey() != null){
                    o.setGuestID(check.getKey());
                } else {
                    o.setRegisteredID(check.getValue());
                }
                o.setAction(rs.getString("Action"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static AuditTrail createAuditTrail(AuditTrail auditTrail) {
        String sql = "INSERT INTO AuditTrail (RegisteredID, GuestID, Action, Timestamp) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, auditTrail.getRegisteredID().getUserID());
            stmt.setInt(2, auditTrail.getGuestID().getUserID());
            stmt.setString(3, auditTrail.getAction());
            stmt.setString(4, auditTrail.getTimestamp());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating audit trail failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    auditTrail.setAuditID((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating audit trail failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return auditTrail;
    }

    public static boolean updateAuditTrail(AuditTrail auditTrail) {
        String sql = "UPDATE AuditTrail SET RegisteredID = ?, GuestID = ?, Action = ?, Timestamp = ? WHERE AuditID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, auditTrail.getRegisteredID().getUserID());
            stmt.setInt(2, auditTrail.getGuestID().getUserID());
            stmt.setString(3, auditTrail.getAction());
            stmt.setString(4, auditTrail.getTimestamp());
            stmt.setInt(5, auditTrail.getAuditID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteAuditTrail(int auditID) {
        String sql = "DELETE FROM AuditTrail WHERE AuditID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, auditID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
