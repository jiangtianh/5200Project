package test;

import dal.UsersDao;
import model.User;

import java.sql.Date;
import java.sql.SQLException;

public class UserTest {
    public static void main(String[] args) {
        UsersDao usersDao = UsersDao.getInstance();

        // Create a new user
        User newUser = new User("testuser", "testpassword", "Test", "User", Date.valueOf("2000-01-01"), "INTJ", User.Profession.Software_Developer);
        try {
            usersDao.create(newUser);
            System.out.println("User created: " + newUser.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Retrieve the user by username
        try {
            User retrievedUser = usersDao.getUserByUsername("testuser");
            System.out.println("User retrieved: " + retrievedUser.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Update the user's password
        try {
            User updatedUser = usersDao.updatePassword(newUser, "newpassword");
            System.out.println("User password updated: " + updatedUser.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Delete the user
        try {
            usersDao.delete(newUser);
            System.out.println("User deleted: " + newUser.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}