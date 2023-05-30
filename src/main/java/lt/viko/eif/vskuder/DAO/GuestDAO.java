package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.Guest;

import java.sql.*;

public class GuestDAO extends DAO{
    public final String database_name = "Guests";
    public static Guest getGuest(int id){
        String sql = "Select * " +
                "from Guests " +
                "where GuestID = " + id;

        Guest o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new Guest();
                o.setUserID(rs.getInt("GuestID"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Guest createGuest(Guest guest) {
        String sql = "INSERT INTO Guests (GuestID) VALUES (NULL)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating Guest failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guest.setUserID((int) generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Creating Guest failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return guest;
    }

    public static boolean deleteGuest(int guestID) {
        String sql = "DELETE FROM Guests WHERE GuestID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guestID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
