package lt.viko.eif.vskuder.models;

public class Policy {

    private Integer policyID;
    private String policyName;
    private Description policyDescription;

    public Policy(){}

    public Integer getPolicyID() {
        return policyID;
    }

    public void setPolicyID(Integer policyID) {
        this.policyID = policyID;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Description getPolicyDescription() {
        return policyDescription;
    }

    public void setPolicyDescription(Description policyDescription) {
        this.policyDescription = policyDescription;
    }

    @Override
    public String toString() {
        return "Policy\n{\n" +
                "policyID: " + policyID + "\n" +
                "policyName: " + policyName + "\n" +
                "policyDescription: " + policyDescription + "\n" +
                '}';
    }
}
