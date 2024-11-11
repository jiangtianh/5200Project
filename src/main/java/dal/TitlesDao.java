package dal;

import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitlesDao {
    protected ConnectionManager connectionManager;

    private static TitlesDao instance = null;
    protected TitlesDao() {
        connectionManager = new ConnectionManager();
    }
    public static TitlesDao getInstance() {
        if (instance == null) {
            instance = new TitlesDao();
        }
        return instance;
    }


    public Title getTitleById(String titleId) throws SQLException {
        String selectTitle = "SELECT titleId, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes FROM Titles WHERE titleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitle);
            selectStmt.setString(1, titleId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultTitleId = results.getString("titleId");
                String titleType = results.getString("titleType");
                String primaryTitle = results.getString("primaryTitle");
                String originalTitle = results.getString("originalTitle");
                boolean isAdult = results.getBoolean("isAdult");
                int startYear = results.getInt("startYear");
                int endYear = results.getInt("endYear");
                int runtimeMinutes = results.getInt("runtimeMinutes");
                Title title = new Title(resultTitleId, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes);
                return title;
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
        return null;
    }


    public List<Title> getTitleByPrimaryTitle(String primaryTitle, int page, int pageSize) throws SQLException {
        String selectTitle = "SELECT titleId, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes " +
                "FROM Titles WHERE LOWER(primaryTitle) LIKE ? LIMIT ? OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        List<Title> titles = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitle);
            selectStmt.setString(1, "%" + primaryTitle.toLowerCase() + "%");
            selectStmt.setInt(2, pageSize);
            selectStmt.setInt(3, (page - 1) * pageSize);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String resultTitleId = results.getString("titleId");
                String titleType = results.getString("titleType");
                String originalTitle = results.getString("originalTitle");
                boolean isAdult = results.getBoolean("isAdult");
                int startYear = results.getInt("startYear");
                int endYear = results.getInt("endYear");
                int runtimeMinutes = results.getInt("runtimeMinutes");
                Title title = new Title(resultTitleId, titleType, results.getString("primaryTitle"), originalTitle, isAdult, startYear, endYear, runtimeMinutes);
                titles.add(title);
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
        return titles;
    }




}
