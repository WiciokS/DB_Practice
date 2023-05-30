package lt.viko.eif.vskuder.models;

public class GuestProgramAssociation {
    private AIProgram programID;

    public GuestProgramAssociation(){}

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    @Override
    public String toString() {
        return "GuestProgramAssociation\n{\n" +
                "programID: " + programID + "\n" +
                '}';
    }
}
