package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.GuestProgramAssociation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramListGuestDAO extends DAO{
    public static List<GuestProgramAssociation> getGuestProgramAssociations(){
        List<GuestProgramAssociation> associations = new ArrayList<>();
        String sql = "SELECT * FROM ProgramList_Guests";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                GuestProgramAssociation o = new GuestProgramAssociation();
                o.setProgramID(AIProgramDAO.getAIProgram(rs.getInt("ProgramID")));
                associations.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return associations;
    }

    public static GuestProgramAssociation createGuestProgramAssociation(GuestProgramAssociation guestProgramAssociation) {
        String sql = "INSERT INTO ProgramList_Guests (ProgramID) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, guestProgramAssociation.getProgramID().getProgramID());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating guest program association failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return guestProgramAssociation;
    }

    public static boolean deleteGuestProgramAssociation(int programID) {
        String sql = "DELETE FROM ProgramList_Guests WHERE ProgramID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, programID);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
