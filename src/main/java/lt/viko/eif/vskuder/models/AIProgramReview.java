package lt.viko.eif.vskuder.models;

import java.util.ArrayList;
import java.util.List;

public class AIProgramReview {
    private Integer reviewID;
    private AIProgram programID;
    private User userID;
    private String review;

    public AIProgramReview(){}

    public Integer getReviewID() {
        return reviewID;
    }

    public void setReviewID(Integer reviewID) {
        this.reviewID = reviewID;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
