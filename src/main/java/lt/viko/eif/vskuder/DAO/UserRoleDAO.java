package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.UserRole;
import lt.viko.eif.vskuder.models.User;
import lt.viko.eif.vskuder.models.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDAO extends DAO{

    public final String database_name = "UserRoles";
    public static UserRole getUserRole(int id){
        String sql = "Select * " +
                "from UserRoles " +
                "where UserID = " + id;

        UserRole o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new UserRole();
                o.setUserID(UserDAO.getUser(rs.getInt("UserID")));
                o.setRoleID(RoleDAO.getRoleById(rs.getInt("RoleID")));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static UserRole createUserRole(User user, Role role) {
        String sql = "INSERT INTO UserRoles (UserID, RoleID) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, DAO.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, user.getUserID());
            stmt.setInt(2, role.getRoleID());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                UserRole newUserRole = new UserRole();
                newUserRole.setUserID(user);
                newUserRole.setRoleID(role);
                return newUserRole;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean deleteUserRole(int userId) {
        String sql = "DELETE FROM UserRoles WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUserRole(UserRole userRole) {
        String sql = "UPDATE UserRoles SET RoleID = ? WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userRole.getRoleID().getRoleID());
            stmt.setInt(2, userRole.getUserID().getUserID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<UserRole> getAllUserRoles() {
        List<UserRole> userRoles = new ArrayList();
        String sql = "SELECT * FROM UserRoles";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UserRole userRole = new UserRole();
                User user = UserDAO.getUser(rs.getInt("UserID"));
                Role role = RoleDAO.getRoleById(rs.getInt("RoleID"));
                userRole.setUserID(user);
                userRole.setRoleID(role);
                userRoles.add(userRole);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userRoles;
    }
}
