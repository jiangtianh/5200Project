package service;

import dal.GenresDao;
import dal.RatingsDao;
import dal.ReviewsDao;
import dal.TitleGenreDao;
import dal.TitlesDao;
import model.Genre;
import model.Title;
import model.Review;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RecommendationService {

    private RatingsDao ratingsDao;
    private TitlesDao titlesDao;
    private TitleGenreDao titleGenreDao;
    private ReviewsDao reviewsDao;

    public RecommendationService() {
        this.ratingsDao = RatingsDao.getInstance();
        this.titlesDao = TitlesDao.getInstance();
        this.titleGenreDao = TitleGenreDao.getInstance();
        this.reviewsDao = ReviewsDao.getInstance();
    }

    // 1. Recommend by Similar Genres
    public List<Title> recommendBySimilarGenres(int userId) throws SQLException {
        List<Review> userReviews = reviewsDao.getReviewByUsername(String.valueOf(userId));
        List<Genre> preferredGenres = new ArrayList<>();

        for (Review review : userReviews) {
            if (review.getRating() >= 8) { // Filtering high-rated reviews
                preferredGenres.addAll(titleGenreDao.getGenresForTitle(review.getTitle()));
            }
        }

        Set<Title> recommendedTitles = new HashSet<>();
        for (Genre genre : preferredGenres) {
            List<Title> genreTitles = titleGenreDao.getTitlesByGenre(genre);
            for (Title title : genreTitles) {
                if (ratingsDao.getRatingByTitle(title).getAverageRating() >= 8) {
                    recommendedTitles.add(title);
                }
            }
        }

        return new ArrayList<>(recommendedTitles);
    }

    // 2. Recommend Top-Rated Titles for New Users
    public List<Title> recommendTopRatedTitles() throws SQLException {
        // Retrieving top-rated titles with minimum votes and high ratings
        return ratingsDao.getTopTitlesByRatingAndVotes(100, 8.0, 500); // Example parameters
    }
}
