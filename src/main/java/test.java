import dal.GenresDao;
import model.Genre;

import java.sql.SQLException;
import java.util.List;

public class test {

    public static void main(String[] args) {
        GenresDao genresDao = GenresDao.getInstance();

        try {
            // Test getGenreByGenreId
            Genre genreById = genresDao.getGenreByGenreId(1);
            if (genreById != null) {
                System.out.println("Genre by ID 1: " + genreById.getGenreName());
            } else {
                System.out.println("Genre by ID 1 not found.");
            }

            // Test getAllGenres
            List<Genre> allGenres = genresDao.getAllGenres();
            System.out.println("All Genres:");
            for (Genre genre : allGenres) {
                System.out.println("ID: " + genre.getGenreId() + ", Name: " + genre.getGenreName());
            }

            // Test getGenreByName
            Genre genreByName = genresDao.getGenreByName("Documentary");
            if (genreByName != null) {
                System.out.println("Genre by name 'Documentary': " + genreByName.getGenreName());
            } else {
                System.out.println("Genre by name 'Documentary' not found.");
            }

            // Test getGenreByInvalidId
            Genre invalidGenreById = genresDao.getGenreByGenreId(-1);
            if (invalidGenreById == null) {
                System.out.println("Genre by invalid ID -1 not found.");
            }

            // Test getGenreByInvalidName
            Genre invalidGenreByName = genresDao.getGenreByName("InvalidGenre");
            if (invalidGenreByName == null) {
                System.out.println("Genre by invalid name 'InvalidGenre' not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
