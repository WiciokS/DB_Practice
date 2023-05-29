package lt.viko.eif.vskuder.models;


public class ProgramDeveloper {

    private AIProgram programID;

    private Developer developerID;

    public ProgramDeveloper(){}

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    public Developer getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(Developer developerID) {
        this.developerID = developerID;
    }
}
