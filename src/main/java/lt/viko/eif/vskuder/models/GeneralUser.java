package lt.viko.eif.vskuder.models;

public class GeneralUser {
    Integer userID;

    boolean isGuest;

    public Integer getUserID(){
        return userID;
    }

    public void setUserID(Integer userID){
        this.userID = userID;
    }

    public boolean getIsGuest(){
        return isGuest;
    }

    public boolean setIsGuest(boolean isGuest){
        return this.isGuest = isGuest;
    }

    @Override
    public String toString() {
        return "GeneralUser\n{\n" +
                "userID=" + userID +
                ", isGuest=" + isGuest +
                '}';
    }
}
