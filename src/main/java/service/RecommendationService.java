package service;

import dal.GenresDao;
import dal.RatingsDao;
import dal.ReviewsDao;
import dal.TitleGenreDao;
import dal.TitlesDao;
import model.Genre;
import model.Title;
import model.Review;
import model.User;

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
    public List<Title> recommendBySimilarGenres(User user, int pageNumber, int pageSize) throws SQLException {
        List<Review> userReviews = reviewsDao.getReviewByUsername(user.getUsername());

        //DELETE LATER
        for (Review review : userReviews) {
            System.out.println(review.getTitle().getPrimaryTitle());
        }

        if (userReviews.isEmpty()) {
            return recommendTopRatedTitles();
        }

        Set<Integer> preferredGenreIds = new HashSet<>();
        for (Review review : userReviews) {
            if (review.getRating() >= 8) { // Filtering high-rated reviews
                List<Genre> genres = titleGenreDao.getGenresForTitle(review.getTitle());
                for (Genre genre : genres) {
                    preferredGenreIds.add(genre.getGenreId());
                }
            }
        }

        System.out.println("Preferred Genres: ");
        for (Integer genreid : preferredGenreIds) {
            System.out.println(genreid);
        }

        if(preferredGenreIds.isEmpty()) {
            return recommendTopRatedTitles();
        }

        double minRating = 8.0;
        int minVotes = 10000;
        List<Title> recommendedTitles = titleGenreDao.getTitlesByGenresAndRating(
                new ArrayList<>(preferredGenreIds), minRating, minVotes, pageNumber, pageSize
        );

        return recommendedTitles;
    }

    // 2. Recommend Top-Rated Titles for New Users
    public List<Title> recommendTopRatedTitles() throws SQLException {
        // Retrieving top-rated titles with minimum votes and high ratings
        System.out.println("Returning top-rated titles: ");
        return ratingsDao.getTopTitlesByRatingAndVotes(100, 8.0, 10000); // Example parameters
    }
}
