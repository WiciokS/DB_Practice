package lt.viko.eif.vskuder.models;

public class UserRole {
    private User userID;
    private Role roleID;

    public UserRole() {
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Role getRoleID() {
        return roleID;
    }

    public void setRoleID(Role roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "UserRole\n{\n" +
                "userID: " + userID + "\n" +
                "roleID: " + roleID + "\n" +
                '}';
    }
}
