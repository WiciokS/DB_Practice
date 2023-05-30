package lt.viko.eif.vskuder.models;

public class Description {
    private Integer descriptionID;
    private String descriptionText;

    public Description(){}

    public Integer getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(Integer descriptionID) {
        this.descriptionID = descriptionID;
    }

    public void getDescriptionID(Integer descriptionID) {
        this.descriptionID = descriptionID;
    }

    public String getDescriptionText() {
        return descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    @Override
    public String toString() {
        return "Description\n{\n" +
                "descriptionID: " + descriptionID + "\n" +
                "descriptionText: " + descriptionText + '\n' +
                '}';
    }
}
