package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.RolePermission;
import lt.viko.eif.vskuder.models.Role;
import lt.viko.eif.vskuder.models.Permission;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolePermissionDAO extends DAO{
    public static RolePermission getRolePermission(int id){
        String sql = "Select *" +
                "from RolePermissions" +
                "where RoleID = " + id;

        RolePermission o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new RolePermission();
                o.setRoleID(RoleDAO.getRoleById(rs.getInt("RoleID")));
                o.setPermissionID(PermissionDAO.getPermission(rs.getInt("PermissionID")));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static RolePermission createRolePermission(Role role, Permission permission){
        String sql = "INSERT INTO RolePermissions (RoleID, PermissionID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, role.getRoleID());
            stmt.setInt(2, permission.getPermissionID());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                RolePermission newRolePermission = new RolePermission();
                newRolePermission.setRoleID(role);
                newRolePermission.setPermissionID(permission);
                return newRolePermission;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteRolePermission(int roleId, int permissionId) {
        String sql = "DELETE FROM RolePermissions WHERE RoleID = ? AND PermissionID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, roleId);
            stmt.setInt(2, permissionId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<RolePermission> getAllRolePermissions() {
        List<RolePermission> rolePermissions = new ArrayList<>();
        String sql = "SELECT * FROM RolePermissions";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                RolePermission rolePermission = new RolePermission();
                Role role = RoleDAO.getRoleById(rs.getInt("RoleID"));
                Permission permission = PermissionDAO.getPermission(rs.getInt("PermissionID"));
                rolePermission.setRoleID(role);
                rolePermission.setPermissionID(permission);
                rolePermissions.add(rolePermission);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rolePermissions;
    }
}
