package lt.viko.eif.vskuder.models;

public class AuditTrail {
    private Integer auditID;
    private User registeredID;
    private Guest guestID;
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

    public Guest getGuestID() {
        return guestID;
    }

    public void setGuestID(Guest guestID) {
        this.guestID = guestID;
    }
}