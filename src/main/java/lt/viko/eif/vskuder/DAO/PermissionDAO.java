package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.Permission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAO extends DAO{
    public final String database_name = "Permissions";
    public static Permission getPermission(int id){
        String sql = "Select *" +
                "from Permissions" +
                "where PermissionID = " + id;

        Permission o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new Permission();
                o.setPermissionID(rs.getInt("PermissionID"));
                o.setPermissionName(rs.getString("PermissionName"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Permission createPermission(String permissionName){
        String sql = "INSERT INTO Permissions (PermissionName) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permissionName);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                Permission newPermission = new Permission();
                newPermission.setPermissionName(permissionName);
                return newPermission;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deletePermission(int id) {
        String sql = "DELETE FROM Permissions WHERE PermissionID = ?";

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

    public static boolean updatePermission(Permission permission) {
        String sql = "UPDATE Permissions SET PermissionName = ? WHERE PermissionID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, permission.getPermissionName());
            stmt.setInt(2, permission.getPermissionID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<Permission> getAllPermissions() {
        List<Permission> permissions = new ArrayList();
        String sql = "SELECT * FROM Permissions";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Permission permission = new Permission();
                permission.setPermissionID(rs.getInt("PermissionID"));
                permission.setPermissionName(rs.getString("PermissionName"));
                permissions.add(permission);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return permissions;
    }
}
