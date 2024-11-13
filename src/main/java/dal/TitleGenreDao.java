package dal;

import model.Genre;
import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitleGenreDao {
    protected ConnectionManager connectionManager;

    private static TitleGenreDao instance = null;
    protected TitleGenreDao() {
        connectionManager = new ConnectionManager();
    }
    public static TitleGenreDao getInstance() {
        if (instance == null) {
            instance = new TitleGenreDao();
        }
        return instance;
    }
    
    public List<Title> getTitlesByGenre(Genre genre) throws SQLException {
        List<Title> titles = new ArrayList<>();
        String selectTitles = "SELECT t.titleId, t.titleType, t.primaryTitle, t.originalTitle, " +
                              "t.isAdult, t.startYear, t.endYear, t.runtimeMinutes " +
                              "FROM Titles AS t " +
                              "JOIN TitleGenres AS tg ON t.titleId = tg.titleId " +
                              "WHERE tg.genreId = ?;";
        
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitles);
            selectStmt.setInt(1, genre.getGenreId());
            results = selectStmt.executeQuery();

            while (results.next()) {
                Title title = new Title(
                    results.getString("titleId"),
                    results.getString("titleType"),
                    results.getString("primaryTitle"),
                    results.getString("originalTitle"),
                    results.getBoolean("isAdult"),
                    results.getInt("startYear"),
                    results.getInt("endYear"),
                    results.getInt("runtimeMinutes")
                );
                titles.add(title);
            }
        } finally {
            if (results != null) results.close();
            if (selectStmt != null) selectStmt.close();
            if (connection != null) connection.close();
        }
        return titles;
    }

    public List<Genre> getGenresForTitle(Title title) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        String selectGenres = "SELECT genreId FROM TitleGenres WHERE titleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectGenres);
            selectStmt.setString(1, title.getTitleId());
            results = selectStmt.executeQuery();

            GenresDao genresDao = GenresDao.getInstance();
            while (results.next()) {
                int genreId = results.getInt("genreId");
                Genre genre = genresDao.getGenreByGenreId(genreId);
                if (genre != null) {
                    genres.add(genre);
                }
            }

        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connectionManager.closeConnection(connection);
            }
        }
        return genres;
    }

}
