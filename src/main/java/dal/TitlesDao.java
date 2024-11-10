package dal;

import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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


    public Title getTitleById(int titleId) throws SQLException {
        String selectTitle = "SELECT titleId, titleType, primaryTitle, originalTitle, isAdult, startYear, endYear, runtimeMinutes FROM Titles WHERE titleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitle);
            selectStmt.setInt(1, titleId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultTitleId = results.getString("titleId");
                Title.TitleType titleType = Title.TitleType.valueOf(results.getString("titleType"));
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
}
