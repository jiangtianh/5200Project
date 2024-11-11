package dal;

import model.Person;
import model.Principals;
import model.Title;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalsDao {
    protected ConnectionManager connectionManager;

    private static PrincipalsDao instance = null;
    protected PrincipalsDao() {
        connectionManager = new ConnectionManager();
    }
    public static PrincipalsDao getInstance() {
        if (instance == null) {
            instance = new PrincipalsDao();
        }
        return instance;
    }


    public List<Principals> getPrincipalsForTitle(Title title) throws SQLException {
        List<Principals> principals = new ArrayList<>();
        String selectPrincipals = "SELECT titleId, ordering, personId, category, job, characters FROM Principals WHERE titleId=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;

        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectPrincipals);
            selectStmt.setString(1, title.getTitleId());
            results = selectStmt.executeQuery();

            PersonDao personDao = PersonDao.getInstance();

            while (results.next()) {
                String resultTitleId = results.getString("titleId");
                int ordering = results.getInt("ordering");
                String personId = results.getString("personId");
                Principals.JobCategory category = Principals.JobCategory.valueOf(results.getString("category"));
                String job = results.getString("job");
                String characters = results.getString("characters");
                Person person = personDao.getPersonByPersonId(personId);
                Principals principal = new Principals(title, ordering, person, category, job, characters);
                principals.add(principal);
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
        return principals;
    }
}
