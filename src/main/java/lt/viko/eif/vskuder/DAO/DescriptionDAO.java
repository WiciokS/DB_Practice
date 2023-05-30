package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgram;
import lt.viko.eif.vskuder.models.Description;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DescriptionDAO extends DAO{
    public final String database_name = "Descriptions";

    public static Description getDescription(int id) {
        String sql = "Select * " +
                "from Descriptions " +
                "where DescriptionID = " + id;

        Description o = new Description();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                o.setDescriptionID(rs.getInt("DescriptionID"));
                o.setDescriptionText(rs.getString("DescriptionText"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return o;
    }

    public static Description createDescription(String programDescription) {
        String sql = "INSERT INTO Descriptions (DescriptionText) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, programDescription);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating description failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Description newDescription = new Description();
                    newDescription.setDescriptionID(generatedKeys.getInt(1));
                    newDescription.setDescriptionText(programDescription);
                    return newDescription;
                } else {
                    throw new SQLException("Creating description failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static boolean deleteDescription(int id) {
        String sql = "DELETE FROM Descriptions WHERE DescriptionID = ?";

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

    public static boolean updateDescription(Description description) {
        String sql = "UPDATE Descriptions SET DescriptionText = ? WHERE DescriptionID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, description.getDescriptionText());
            stmt.setInt(2, description.getDescriptionID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Description> getAllDescriptions() {
        List<Description> descriptions = new ArrayList();
        String sql = "SELECT * FROM Descriptions";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Description description = new Description();
                description.setDescriptionID(rs.getInt("DescriptionID"));
                description.setDescriptionText(rs.getString("DescriptionText"));
                descriptions.add(description);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return descriptions;
    }
}
