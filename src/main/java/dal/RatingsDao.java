package dal;

import model.Rating;
import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingsDao {
    protected ConnectionManager connectionManager;

    private static RatingsDao instance = null;
    protected RatingsDao() {
        connectionManager = new ConnectionManager();
    }
    public static RatingsDao getInstance() {
        if (instance == null) {
            instance = new RatingsDao();
        }
        return instance;
    }


    // Only called when the Rating does not exist for the Title
    public Rating create(Rating rating) {
        String insertRating = "INSERT INTO Ratings(TitleId, AverageRating, NumVotes) VALUES(?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertRating);
            insertStmt.setString(1, rating.getTitle().getTitleId());
            insertStmt.setDouble(2, rating.getAverageRating());
            insertStmt.setInt(3, rating.getNumVotes());
            insertStmt.executeUpdate();

            return rating;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (insertStmt != null) {
                try {
                    insertStmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Rating getRatingByTitle(Title title) throws SQLException {
        String selectRating = "SELECT TitleId, AverageRating, NumVotes FROM Ratings WHERE TitleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectRating);
            selectStmt.setString(1, title.getTitleId());
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultTitleId = results.getString("TitleId");
                double averageRating = results.getDouble("AverageRating");
                int numVotes = results.getInt("NumVotes");
                Rating rating = new Rating(title, averageRating, numVotes);
                return rating;
            }
        } finally {
            if (results != null) {
                results.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }


    public Rating update(Rating rating) throws SQLException {
        String updateRating = "UPDATE Ratings SET AverageRating=?, NumVotes=? WHERE TitleId=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;

        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updateRating);
            updateStmt.setDouble(1, rating.getAverageRating());
            updateStmt.setInt(2, rating.getNumVotes());
            updateStmt.setString(3, rating.getTitle().getTitleId());
            updateStmt.executeUpdate();

            return rating;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }
}
