package model;

public class Review {
    private int reviewId;
    private Title title;
    private User user;
    private float rating;
    private String content;

    public Review(int reviewId, Title title, User user, float rating, String content) {
        this.reviewId = reviewId;
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }
    public Review(Title title, User user, float rating, String content) {
        this.title = title;
        this.user = user;
        this.rating = rating;
        this.content = content;
    }
    public Review (int reviewId) {
        this.reviewId = reviewId;
    }
    public Review (Title title) {
        this.title = title;
    }
    public Review (User user) {
        this.user = user;
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
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
