package dal;

import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {
    protected ConnectionManager connectionManager;

    private static PersonDao instance = null;
    protected PersonDao() {
        connectionManager = new ConnectionManager();
    }
    public static PersonDao getInstance() {
        if (instance == null) {
            instance = new PersonDao();
        }
        return instance;
    }


    public Person getPersonByPersonId(String personId) throws SQLException {
        String selectPerson = "SELECT personId, name, birthYear, deathYear, primaryProfession FROM People WHERE personId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, personId);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultPersonId = results.getString("personId");
                String name = results.getString("name");
                int birthYear = results.getInt("birthYear");
                int deathYear = results.getInt("deathYear");
                String primaryProfession = results.getString("primaryProfession");
                Person person = new Person(resultPersonId, name, birthYear, deathYear, primaryProfession);
                return person;
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


    public List<Person> getPersonsByName(String name, int page, int pageSize) throws SQLException {
        String selectPerson = "SELECT personId, name, birthYear, deathYear, primaryProfession " +
                "FROM People WHERE LOWER(name) LIKE ? LIMIT ? OFFSET ?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        List<Person> persons = new ArrayList<>();

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPerson);
            selectStmt.setString(1, "%" + name.toLowerCase() + "%");
            selectStmt.setInt(2, pageSize);
            selectStmt.setInt(3, (page - 1) * pageSize);
            results = selectStmt.executeQuery();

            while (results.next()) {
                String personId = results.getString("personId");
                String resultName = results.getString("name");
                int birthYear = results.getInt("birthYear");
                int deathYear = results.getInt("deathYear");
                String primaryProfession = results.getString("primaryProfession");
                Person person = new Person(personId, resultName, birthYear, deathYear, primaryProfession);
                persons.add(person);
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
        return persons;
    }



}

