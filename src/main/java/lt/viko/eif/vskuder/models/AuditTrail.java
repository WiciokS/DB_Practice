package lt.viko.eif.vskuder.models;

public class AuditTrail {
    private Integer auditID;
    private User registeredID;
    private String action;
    private String timestamp;

    public AuditTrail(){}

    public Integer getAuditID() {
        return auditID;
    }

    public void setAuditID(Integer auditID) {
        this.auditID = auditID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public User getRegisteredID() {
        return registeredID;
    }

    public void setRegisteredID(User registeredID) {
        this.registeredID = registeredID;
    }

    @Override
    public String toString() {
        return "AuditTrail\n{\n" +
                "auditID: " + auditID + "\n" +
                "registeredID: " + registeredID + "\n" +
                "action: " + action + "\n" +
                "timestamp: " + timestamp + "\n" +
                '}';
    }
}