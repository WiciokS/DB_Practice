package lt.viko.eif.vskuder.models;

import java.util.ArrayList;
import java.util.List;

public class AIProgramRating {

    private Integer ratingID;
    private AIProgram programID;
    private User userID;
    private Integer rating;

    public AIProgramRating(){}

    public Integer getRatingID() {
        return ratingID;
    }

    public void setRatingID(Integer ratingID) {
        this.ratingID = ratingID;
    }

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    public User getUserID() {
        return userID;
    }

    public void setUserID(User userID) {
        this.userID = userID;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "AIProgramRating\n{\n" +
                "RatingID: " + ratingID + "\n" +
                "Program: " + programID.toString() + "\n" +
                "User:" + userID.toString() + "\n" +
                "Rating: " + rating + "\n" +
                "}";
    }
}
