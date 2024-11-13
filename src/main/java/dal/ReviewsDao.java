package dal;

import model.Rating;
import model.Review;
import model.Title;
import model.User;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewsDao {
    protected ConnectionManager connectionManager;

    private static ReviewsDao instance = null;
    protected ReviewsDao() {
        connectionManager = new ConnectionManager();
    }
    public static ReviewsDao getInstance() {
        if (instance == null) {
            instance = new ReviewsDao();
        }
        return instance;
    }


    public Review create(Review review) throws SQLException {
        String insertReview = "INSERT INTO Reviews (titleId, username, rating, content) VALUES(?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;
        ResultSet resultKey = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
            insertStmt.setString(1, review.getTitle().getTitleId());
            insertStmt.setString(2, review.getUser().getUsername());
            insertStmt.setDouble(3, review.getRating());
            insertStmt.setString(4, review.getContent());
            insertStmt.executeUpdate();

            resultKey = insertStmt.getGeneratedKeys();
            int reviewId = -1;
            if (resultKey.next()) {
                reviewId = resultKey.getInt(1);

                // Update the rating for the title
                Rating originalRating = RatingsDao.getInstance().getRatingByTitle(review.getTitle());
                int newNumVotes = originalRating.getNumVotes() + 1;
                double newRating = (originalRating.getAverageRating() * originalRating.getNumVotes() + review.getRating()) / newNumVotes;
                RatingsDao.getInstance().update(new Rating(review.getTitle(), newRating, newNumVotes));

            } else {
                throw new SQLException("Unable to retrieve auto-generated key.");
            }
            review.setReviewId(reviewId);
            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
            if (resultKey != null) {
                resultKey.close();
            }
        }
    }


    public Review getReviewById(int reviewId) throws SQLException {
        String selectReview = "SELECT * FROM Reviews WHERE reviewId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReview);
            selectStmt.setInt(1, reviewId);
            results = selectStmt.executeQuery();
            if (results.next()) {
                String titleId = results.getString("titleId");
                Title title = TitlesDao.getInstance().getTitleById(titleId);
                String username = results.getString("username");
                User user = UsersDao.getInstance().getUserByUsername(username);
                double rating = results.getDouble("rating");
                String content = results.getString("content");

                Review review = new Review(reviewId, title, user, rating, content);
                return review;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public Review update(Review review) throws SQLException {
        String updateReview = "UPDATE Reviews SET rating=?, content=? WHERE reviewId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateReview);
            updateStmt.setDouble(1, review.getRating());
            updateStmt.setString(2, review.getContent());
            updateStmt.setInt(3, review.getReviewId());
            updateStmt.executeUpdate();

            // Update the rating for the title
            double oldRating = getReviewById(review.getReviewId()).getRating();
            Rating originalRating = RatingsDao.getInstance().getRatingByTitle(review.getTitle());
            double newRating = review.getRating();
            double updatedRating = (originalRating.getAverageRating() * originalRating.getNumVotes() - oldRating + newRating) / originalRating.getNumVotes();
            RatingsDao.getInstance().update(new Rating(review.getTitle(), updatedRating, originalRating.getNumVotes()));

            return review;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public Review delete(Review review) throws SQLException {
        String deleteReview = "DELETE FROM Reviews WHERE reviewId=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteReview);
            deleteStmt.setInt(1, review.getReviewId());
            deleteStmt.executeUpdate();

            // Update the rating for the title
            Rating originalRating = RatingsDao.getInstance().getRatingByTitle(review.getTitle());
            int newNumVotes = originalRating.getNumVotes() - 1;
            double newRating = (originalRating.getAverageRating() * originalRating.getNumVotes() - review.getRating()) / newNumVotes;
            RatingsDao.getInstance().update(new Rating(review.getTitle(), newRating, newNumVotes));

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }


    public List<Review> getReviewsByTitle(Title title) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String selectReviews = "SELECT reviewId, titleId, username, rating, content FROM Reviews WHERE titleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, title.getTitleId());
            results = selectStmt.executeQuery();
            while (results.next()) {
                int reviewId = results.getInt("reviewId");
                String username = results.getString("username");
                User user = UsersDao.getInstance().getUserByUsername(username);
                double rating = results.getDouble("rating");
                String content = results.getString("content");

                Review review = new Review(reviewId, title, user, rating, content);
                reviews.add(review);
            }
            return reviews;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }


    public List<Review> getReviewByUsername(String username) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String selectReviews = "SELECT reviewId, titleId, username, rating, content FROM Reviews WHERE username=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectReviews);
            selectStmt.setString(1, username);
            results = selectStmt.executeQuery();
            while (results.next()) {
                int reviewId = results.getInt("reviewId");
                String titleId = results.getString("titleId");
                Title title = TitlesDao.getInstance().getTitleById(titleId);
                double rating = results.getDouble("rating");
                String content = results.getString("content");

                Review review = new Review(reviewId, title, new User(username), rating, content);
                reviews.add(review);
            }
            return reviews;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
    }

}
