package lt.viko.eif.vskuder.models;
public class RegisteredUserProgramAssociation {
    private User UserID;
    private AIProgram programID;

    public RegisteredUserProgramAssociation(){}

    public User getUserID() {
        return UserID;
    }

    public void setUserID(User userID) {
        UserID = userID;
    }

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }
}
