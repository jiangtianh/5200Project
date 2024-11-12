package dal;

import model.Person;
import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitleWriterDao {
    protected ConnectionManager connectionManager;

    private static TitleWriterDao instance = null;
    protected TitleWriterDao() {
        connectionManager = new ConnectionManager();
    }
    public static TitleWriterDao getInstance() {
        if (instance == null) {
            instance = new TitleWriterDao();
        }
        return instance;
    }


    public List<Person> getWritersForTitle(Title title) throws SQLException {
        List<Person> directors = new ArrayList<>();
        String selectDirectors = "SELECT titleId, personId FROM TitleWriters WHERE titleId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectDirectors);
            selectStmt.setString(1, title.getTitleId());
            results = selectStmt.executeQuery();

            PersonDao personDao = PersonDao.getInstance();

            while (results.next()) {
                String personId = results.getString("personId");
                Person person = personDao.getPersonByPersonId(personId);
                directors.add(person);
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
        return directors;
    }

    public List<Title> getTitlesForWriter(Person person) throws SQLException {
        List<Title> titles = new ArrayList<>();
        String selectTitles = "SELECT titleId FROM TitleWriters WHERE personId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitles);
            selectStmt.setString(1, person.getPersonId());
            results = selectStmt.executeQuery();

            while (results.next()) {
                String titleId = results.getString("titleId");
                Title title = TitlesDao.getInstance().getTitleById(titleId);
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
