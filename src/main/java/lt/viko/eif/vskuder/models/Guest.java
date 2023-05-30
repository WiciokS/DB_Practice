package lt.viko.eif.vskuder.models;

public class Guest extends GeneralUser{
    private final boolean isGuest = true;

    public Guest(){}

    @Override
    public String toString() {
        return "Guest\n{\n" +
                "guestID=" + userID + "\n" +
                '}';
    }
}
