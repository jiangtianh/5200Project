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


//    public Title getTitleById(int titleId) throws SQLException {
//        String selectTitle = "SELECT titleId, titleName FROM Titles WHERE titleId=?;";
//
//        try (Connection connection = connectionManager.getConnection();
//             PreparedStatement selectStmt = connection.prepareStatement(selectTitle)) {
//            selectStmt.setInt(1, titleId);
//
//            try (ResultSet results = selectStmt.executeQuery()) {
//                if (results.next()) {
//                    int resultTitleId = results.getInt("titleId");
//                    String titleName = results.getString("titleName");
//                    Title title = new Title(resultTitleId, titleName);
//                    return title;
//                }
//            }
//        }
//        return null;
//    }
}
