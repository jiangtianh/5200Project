package dal;

import model.Person;
import model.Title;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeopleTitlesDao {
    protected ConnectionManager connectionManager;

    private static PeopleTitlesDao instance = null;
    protected PeopleTitlesDao() {
        connectionManager = new ConnectionManager();
    }
    public static PeopleTitlesDao getInstance() {
        if (instance == null) {
            instance = new PeopleTitlesDao();
        }
        return instance;
    }

    public List<Title> getTitlesFamousForPerson(Person person) {
        List<Title> titles = new ArrayList<>();
        String selectTitles = "SELECT titleId FROM PeopleTitles WHERE personId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitles);
            selectStmt.setString(1, person.getPersonId());
            results = selectStmt.executeQuery();

            TitlesDao titleDao = TitlesDao.getInstance();

            while (results.next()) {
                String titleId = results.getString("titleId");
                Title title = titleDao.getTitleById(titleId);
                titles.add(title);
            }
            return titles;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
