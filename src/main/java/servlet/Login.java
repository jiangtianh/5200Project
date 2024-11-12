package servlet;

import dal.UsersDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class Login extends HttpServlet {
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        usersDao = UsersDao.getInstance();
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = usersDao.getUserByUsername(username);
            if (user == null || !user.getPassword().equals(password)) {
                req.setAttribute("errorMessage", "Invalid username or password");
                req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("loggedInUser", user);
                resp.sendRedirect("index.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
