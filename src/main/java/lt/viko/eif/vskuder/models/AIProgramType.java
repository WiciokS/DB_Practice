package lt.viko.eif.vskuder.models;

public class AIProgramType {
    private Integer typeID;
    private String typeName;

    public AIProgramType(){}

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "AIProgramType\n{\n" +
                "typeID:" + typeID + '\n' +
                "typeName: " + typeName + '\n' +
                '}';
    }
}
