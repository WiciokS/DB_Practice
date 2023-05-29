package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DAO{
    public static User getUser(int userID) {
        String sql = "Select *" +
                "from Users" +
                "where UserID = " + userID;

        User o = null;

        try (Connection conn = DriverManager.getConnection(url, user, password)){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                o = new User();
                o.setUserID(rs.getInt("UserID"));
                o.setUserName(rs.getString("UserName"));
                o.setUserEmail(rs.getString("UserEmail"));
                o.setUserPassword(rs.getString("UserPassword"));
            }

            return o;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static User createUser(String userName, String userEmail, String userPassword){
        String sql = "INSERT INTO Users (UserName, UserEmail, UserPassword) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            stmt.setString(2, userEmail);
            stmt.setString(3, userPassword);

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                User newUser = new User();
                newUser.setUserName(userName);
                newUser.setUserEmail(userEmail);
                newUser.setUserPassword(userPassword);
                return newUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE UserID = ?";

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

    public static boolean updateUser(User user) {
        String sql = "UPDATE Users SET UserName = ?, UserEmail = ?, UserPassword = ? WHERE UserID = ?";

        try (Connection conn = DriverManager.getConnection(url, DAO.user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getUserEmail());
            stmt.setString(3, user.getUserPassword());
            stmt.setInt(4, user.getUserID());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("UserID"));
                user.setUserName(rs.getString("UserName"));
                user.setUserEmail(rs.getString("UserEmail"));
                user.setUserPassword(rs.getString("UserPassword"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
