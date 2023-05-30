package lt.viko.eif.vskuder.models;
public class PremiumUserProgramAssociation {
    private User userID;
    private AIProgram programID;

    public PremiumUserProgramAssociation(){}

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return "PremiumUserProgramAssociation\n{\n" +
                "userID: " + userID + "\n" +
                "programID: " + programID + "\n" +
                '}';
    }
}
