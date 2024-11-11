package dal;

import model.Person;
import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TitleDirectorDao {
    protected ConnectionManager connectionManager;

    private static TitleDirectorDao instance = null;
    protected TitleDirectorDao() {
        connectionManager = new ConnectionManager();
    }
    public static TitleDirectorDao getInstance() {
        if (instance == null) {
            instance = new TitleDirectorDao();
        }
        return instance;
    }


    public List<Person> getDirectorsForTitle(Title title) throws SQLException {
        List<Person> directors = new ArrayList<>();
        String selectDirectors = "SELECT titleId, personId FROM Principals WHERE titleId=?";
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

    public List<Title> getTitlesForDirector(Person person) throws SQLException {
        List<Title> titles = new ArrayList<>();
        String selectTitles = "SELECT titleId FROM Principals WHERE personId=?";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectTitles);
            selectStmt.setString(1, person.getPersonId());
            results = selectStmt.executeQuery();

            TitlesDao titlesDao = TitlesDao.getInstance();

            while (results.next()) {
                String titleId = results.getString("titleId");
                Title title = titlesDao.getTitleById(titleId);
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
