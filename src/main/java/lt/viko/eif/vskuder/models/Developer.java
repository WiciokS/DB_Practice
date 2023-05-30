package lt.viko.eif.vskuder.models;

public class Developer {
    private Integer developerID;
    private String developerName;
    private String developerEmail;

    public Developer(){}

    public Integer getDeveloperID() {
        return developerID;
    }

    public void setDeveloperID(Integer developerID) {
        this.developerID = developerID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public void setDeveloperName(String developerName) {
        this.developerName = developerName;
    }

    public String getDeveloperEmail() {
        return developerEmail;
    }

    public void setDeveloperEmail(String developerEmail) {
        this.developerEmail = developerEmail;
    }

    @Override
    public String toString() {
        return "Developer\n{\n" +
                "developerID: " + developerID + "\n" +
                "developerName: " + developerName + "\n" +
                "developerEmail: " + developerEmail + "\n" +
                '}';
    }
}