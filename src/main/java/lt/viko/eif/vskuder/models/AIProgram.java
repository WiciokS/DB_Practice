package lt.viko.eif.vskuder.models;

public class AIProgram {
    private Integer programID;
    private String programName;
    private AIProgramType programType;
    private Description description;

    public AIProgram(){}

    public Integer getProgramID() {
        return programID;
    }

    public void setProgramID(Integer programID) {
        this.programID = programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public AIProgramType getProgramType() {
        return programType;
    }

    public void setProgramType(AIProgramType programType) {
        this.programType = programType;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    @Override
    public String toString(){
        return "AIProgram\n{\n" +
                "ProgramID: " + programID + "\n" +
                "ProgramName: " + programName + "\n" +
                "ProgramType: " + programType.toString() + "\n" +
                "Description: " + description.toString() + "\n" +
                "}";
    }
}
