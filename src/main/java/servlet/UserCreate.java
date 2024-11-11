package servlet;

import dal.UsersDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/createuser")
public class UserCreate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("professions", User.Profession.getAllProfessions());
        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String dobStr = req.getParameter("dob");
        String mbti = req.getParameter("mbti");
        String professionStr = req.getParameter("profession");

        // Parse date of birth
        java.sql.Date dob = null;
        try {
            dob = java.sql.Date.valueOf(dobStr);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            req.setAttribute("messages", "Invalid date format.");
            req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
            return;
        }

        // Parse profession
        User.Profession profession = null;
        try {
            profession = User.Profession.valueOf(professionStr.replace(' ', '_'));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            req.setAttribute("messages", "Invalid profession.");
            req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
            return;
        }

        // Create user object
        User user = new User(username, password, firstName, lastName, dob, mbti, profession);

        // Save user to database
        UsersDao usersDao = UsersDao.getInstance();
        try {
            usersDao.create(user);
            req.setAttribute("messages", "User created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
            req.setAttribute("messages", "Error creating user.");
        }

        req.getRequestDispatcher("/UserCreate.jsp").forward(req, resp);
    }

}
