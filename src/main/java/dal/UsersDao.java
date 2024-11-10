package dal;

import model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsersDao {
    protected ConnectionManager connectionManager;

    private static UsersDao instance = null;
    protected UsersDao() {
        connectionManager = new ConnectionManager();
    }
    public static UsersDao getInstance() {
        if (instance == null) {
            instance = new UsersDao();
        }
        return instance;
    }


    public User create(User user) throws SQLException {
        String insertUser = "INSERT INTO Users (username, password, firstName, lastName, dob, MBTI, Profession) VALUES(?,?,?,?,?,?,?);";
        Connection connection = null;
        PreparedStatement insertStmt = null;

        try {
            connection = connectionManager.getConnection();
            insertStmt = connection.prepareStatement(insertUser);
            insertStmt.setString(1, user.getUsername());
            insertStmt.setString(2, user.getPassword());
            insertStmt.setString(3, user.getFirstName());
            insertStmt.setString(4, user.getLastName());
            insertStmt.setDate(5, user.getDob());
            insertStmt.setString(6, user.getMbti());
            insertStmt.setString(7, user.getProfession().name());
            insertStmt.executeUpdate();

            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (insertStmt != null) {
                insertStmt.close();
            }
        }
    }


    public User getUserByUsername(String username) throws SQLException {
        String selectUser = "SELECT username, password, firstName, lastName, dob, MBTI, Profession FROM Users WHERE username=?;";
        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet results = null;
        try {
            connection = connectionManager.getConnection();
            selectStmt = connection.prepareStatement(selectUser);
            selectStmt.setString(1, username);
            results = selectStmt.executeQuery();

            if (results.next()) {
                String resultUserName = results.getString("username");
                String password = results.getString("password");
                String firstName = results.getString("firstName");
                String lastName = results.getString("lastName");
                java.sql.Date dob = results.getDate("dob");
                String mbti = results.getString("MBTI");
                User.Profession profession = User.Profession.valueOf(results.getString("Profession"));
                User user = new User(resultUserName, password, firstName, lastName, dob, mbti, profession);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (selectStmt != null) {
                selectStmt.close();
            }
            if (results != null) {
                results.close();
            }
        }
        return null;
    }

    public User updatePassword(User user, String newPassword) throws SQLException {
        String updatePassword = "UPDATE Users SET password=? WHERE username=?;";
        Connection connection = null;
        PreparedStatement updateStmt = null;
        try {
            connection = connectionManager.getConnection();
            updateStmt = connection.prepareStatement(updatePassword);
            updateStmt.setString(1, newPassword);
            updateStmt.setString(2, user.getUsername());
            updateStmt.executeUpdate();
            user.setPassword(newPassword);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (updateStmt != null) {
                updateStmt.close();
            }
        }
    }

    public User delete(User user) throws SQLException {
        String deleteUser = "DELETE FROM Users WHERE username=?;";
        Connection connection = null;
        PreparedStatement deleteStmt = null;
        try {
            connection = connectionManager.getConnection();
            deleteStmt = connection.prepareStatement(deleteUser);
            deleteStmt.setString(1, user.getUsername());
            deleteStmt.executeUpdate();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (deleteStmt != null) {
                deleteStmt.close();
            }
        }
    }

}
