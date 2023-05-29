package lt.viko.eif.vskuder.models;
public class ProgramPolicy {
    private AIProgram programID;
    private Policy policyID;

    public ProgramPolicy(){}

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    public Policy getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Policy policyID) {
        this.policyID = policyID;
    }


}
