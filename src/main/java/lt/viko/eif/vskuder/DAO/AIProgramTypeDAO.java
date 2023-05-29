package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.AIProgramType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AIProgramTypeDAO extends DAO{
    public static AIProgramType getAIProgramType(int id) {
        String sql = "Select *" +
                "from AIProgramTypes" +
                "where TypeID = " + id;

        AIProgramType o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new AIProgramType();
                o.setTypeID(rs.getInt("TypeID"));
                o.setTypeName(rs.getString("TypeName"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static AIProgramType createAIProgramType(String typeName){
        String sql = "INSERT INTO AIProgramTypes (TypeName) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, typeName);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                AIProgramType newType = new AIProgramType();
                newType.setTypeName(typeName);
                return newType;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteAIProgramType(int id) {
        String sql = "DELETE FROM AIProgramTypes WHERE TypeID = ?";

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

    public static boolean updateAIProgramType(AIProgramType type) {
        String sql = "UPDATE AIProgramTypes SET TypeName = ? WHERE TypeID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type.getTypeName());
            stmt.setInt(2, type.getTypeID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<AIProgramType> getAllAIProgramTypes() {
        List<AIProgramType> types = new ArrayList();
        String sql = "SELECT * FROM AIProgramTypes";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                AIProgramType type = new AIProgramType();
                type.setTypeID(rs.getInt("TypeID"));
                type.setTypeName(rs.getString("TypeName"));
                types.add(type);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
