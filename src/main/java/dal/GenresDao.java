package dal;

import model.Genre;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenresDao {
    protected ConnectionManager connectionManager;

    private static GenresDao instance = null;

    protected GenresDao() {
        connectionManager = new ConnectionManager();
    }
    public static GenresDao getInstance() {
        if (instance == null) {
            instance = new GenresDao();
        }
        return instance;
    }


    public Genre getGenreByGenreId(int genreId) throws SQLException {
        String selectGenre = "SELECT genreId, genreName FROM Genres WHERE genreId=?;";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectGenre)) {
            selectStmt.setInt(1, genreId);

            try (ResultSet results = selectStmt.executeQuery()) {
                if (results.next()) {
                    int resultGenreId = results.getInt("genreId");
                    String genreName = results.getString("genreName");
                    Genre genre = new Genre(resultGenreId, genreName);
                    return genre;
                }
            }
        }
        return null;
    }

    public List<Genre> getAllGenres() throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String selectGenres = "SELECT genreId, genreName FROM Genres;";

        try (Connection connection = connectionManager.getConnection();
            PreparedStatement selectStmt = connection.prepareStatement(selectGenres)) {
            try (ResultSet results = selectStmt.executeQuery()) {
                while (results.next()) {
                    int genreId = results.getInt("genreId");
                    String genreName = results.getString("genreName");
                    Genre genre = new Genre(genreId, genreName);
                    genres.add(genre);
                }
            }
        }
        return genres;
    }


    public Genre getGenreByName(String genreName) throws SQLException {
        String selectGenre = "SELECT genreId, genreName FROM Genres WHERE genreName=?;";

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement selectStmt = connection.prepareStatement(selectGenre)) {
            selectStmt.setString(1, genreName);

            try (ResultSet results = selectStmt.executeQuery()) {
                if (results.next()) {
                    int resultGenreId = results.getInt("genreId");
                    String resultGenreName = results.getString("genreName");
                    Genre genre = new Genre(resultGenreId, resultGenreName);
                    return genre;
                }
            }
        }
        return null;
    }

}
