package lt.viko.eif.vskuder.models;

public class User extends GeneralUser{
    private String userName;
    private String userEmail;
    private String userPassword;
    private final boolean isGuest = false;

    public User(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "User\n{\n" +
                "UserID: " + userID + "\n" +
                "userName: " + userName + "\n" +
                "userPassword: " + userPassword + "\n" +
                "userEMail: " + userEmail + "\n" +
                "}";
    }
}
