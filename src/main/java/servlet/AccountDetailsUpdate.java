package servlet;

import model.User;
import dal.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/updateaccountdetails")
public class AccountDetailsUpdate extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }
        User user = (User) session.getAttribute("loggedInUser");
        req.setAttribute("user", user);
        req.setAttribute("professions", User.Profession.getAllProfessions());
        req.getRequestDispatcher("/AccountDetailsUpdate.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");
        user.setPassword(req.getParameter("password"));
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setDob(java.sql.Date.valueOf(req.getParameter("dob")));
        user.setMbti(req.getParameter("mbti"));
        user.setProfession(User.Profession.valueOf(req.getParameter("profession")));

        try {
            user = UsersDao.getInstance().updateUser(user);
            session.setAttribute("loggedInUser", user);
            resp.sendRedirect("accountdetails");
        } catch (SQLException e) {
            throw new ServletException("Unable to update user details", e);
        }
    }
}