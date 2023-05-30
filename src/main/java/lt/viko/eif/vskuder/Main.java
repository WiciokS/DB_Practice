package lt.viko.eif.vskuder;

import lt.viko.eif.vskuder.DAO.*;
import lt.viko.eif.vskuder.models.AIProgramRating;
import lt.viko.eif.vskuder.models.AIProgramType;
import lt.viko.eif.vskuder.models.Description;
import lt.viko.eif.vskuder.models.User;

import static lt.viko.eif.vskuder.DAO.AIProgramDAO.createAIProgram;
import static lt.viko.eif.vskuder.DAO.AIProgramTypeDAO.createAIProgramType;
import static lt.viko.eif.vskuder.DAO.DescriptionDAO.createDescription;
import static lt.viko.eif.vskuder.DAO.UserDAO.createUser;
import static lt.viko.eif.vskuder.DAO.UserDAO.getUser;

/*private final String url = "jdbc:mysql://localhost:3306/db_practice";

private final String user = "root";

private final String password = "root";*/
public class Main {
    public static void main(String[] args) {
        AIProgramRating o = AIProgramRatingDAO.getAIProgramRating(1);
        System.out.println(o);
    }
}
































