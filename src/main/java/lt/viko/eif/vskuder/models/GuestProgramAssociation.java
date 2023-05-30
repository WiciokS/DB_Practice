package lt.viko.eif.vskuder.models;

public class GuestProgramAssociation {
    private AIProgram programID;

    private Guest guestID;

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
                "guestID: " + guestID + "\n" +
                '}';
    }

    public Guest getGuestID() {
        return guestID;
    }

    public void setGuestID(Guest guestID) {
        this.guestID = guestID;
    }
}
