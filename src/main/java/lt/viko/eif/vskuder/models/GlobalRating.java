package lt.viko.eif.vskuder.models;

public class GlobalRating {
    private AIProgram programID;
    private Float rating;

    public GlobalRating(){}

    public AIProgram getProgramID() {
        return programID;
    }

    public void setProgramID(AIProgram programID) {
        this.programID = programID;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "GlobalRating\n{\n" +
                "programID: " + programID + "\n" +
                "rating: " + rating + "\n" +
                '}';
    }
}
