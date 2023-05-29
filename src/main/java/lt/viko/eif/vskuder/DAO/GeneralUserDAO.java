package lt.viko.eif.vskuder.DAO;

import lt.viko.eif.vskuder.models.Guest;
import lt.viko.eif.vskuder.models.User;

import java.util.AbstractMap;
import java.util.Map;

public class GeneralUserDAO extends DAO{
    public static Map.Entry<Guest, User> getGuestOrUser(int id){
        // create map entry
        Map.Entry<Guest, User> entry = null;
        // add both guest and user as null to new entry
        entry = new AbstractMap.SimpleEntry<Guest, User>(null, null);
        // get guest by id
        Guest guest = GuestDAO.getGuest(id);
        // get user by id
        User user = UserDAO.getUser(id);
        return entry;
    }
}
