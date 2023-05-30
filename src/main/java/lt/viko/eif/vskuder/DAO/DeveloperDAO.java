package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.Developer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO extends DAO{
    public final String database_name = "Developers";
    public static Developer getDeveloper(int id){
        String sql = "Select *" +
                "from Developers" +
                "where DeveloperID = " + id;

        Developer o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new Developer();
                o.setDeveloperID(rs.getInt("DeveloperID"));
                o.setDeveloperName(rs.getString("DeveloperName"));
                o.setDeveloperEmail(rs.getString("DeveloperEmail"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Developer createDeveloper(String developerName, String developerEmail){
        String sql = "INSERT INTO Developers (DeveloperName, DeveloperEmail) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, developerName);
            stmt.setString(2, developerEmail);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                Developer newDeveloper = new Developer();
                newDeveloper.setDeveloperName(developerName);
                newDeveloper.setDeveloperEmail(developerEmail);
                return newDeveloper;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteDeveloper(int id) {
        String sql = "DELETE FROM Developers WHERE DeveloperID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDeveloper(Developer developer) {
        String sql = "UPDATE Developers SET DeveloperName = ?, DeveloperEmail = ? WHERE DeveloperID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, developer.getDeveloperName());
            stmt.setString(2, developer.getDeveloperEmail());
            stmt.setInt(3, developer.getDeveloperID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();
        String sql = "SELECT * FROM Developers";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Developer developer = new Developer();
                developer.setDeveloperID(rs.getInt("DeveloperID"));
                developer.setDeveloperName(rs.getString("DeveloperName"));
                developer.setDeveloperEmail(rs.getString("DeveloperEmail"));
                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
}
