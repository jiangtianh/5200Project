package model;

public class Rating {
    private Title title;
    private float averageRating;
    private int numVotes;

    public Rating(Title title, float averageRating, int numVotes) {
        this.title = title;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }
    public Rating(Title title) {
        this.title = title;
    }
    public Rating(float averageRating, int numVotes) {
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }

    public float getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }
    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }
}
