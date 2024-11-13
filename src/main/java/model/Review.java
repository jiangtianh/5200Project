package model;

public class Review {
    private int reviewId;
    private Title title;
    private User user;
    private double rating;
    private String content;

    public Review(int reviewId, Title title, User user, double rating, String content) {
        this.reviewId = reviewId;
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }
    public Review(Title title, User user, double rating, String content) {
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }
    public Review (int reviewId) {
        this.reviewId = reviewId;
    }
 



    public int getReviewId() {
        return reviewId;
    }
    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Title getTitle() {
        return title;
    }
    public void setTitle(Title title) {
        this.title = title;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
