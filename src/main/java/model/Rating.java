package model;

public class Rating {
    private Title title;
    private double averageRating;
    private int numVotes;

    public Rating(Title title, double averageRating, int numVotes) {
        this.title = title;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }
    public Rating(Title title) {
        this.title = title;
    }
    public Rating(double averageRating, int numVotes) {
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }

    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getNumVotes() {
        return numVotes;
    }
    public void setNumVotes(int numVotes) {
        this.numVotes = numVotes;
    }
}
