package servlet;

import dal.ReviewsDao;
import model.Review;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reviewsbyuser")
public class ReviewsByUser extends HttpServlet {

    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        User loggedInUser = (session != null) ? (User) session.getAttribute("loggedInUser") : null;
        String username = request.getParameter("username");

        if (username == null || username.trim().isEmpty()) {
            if (loggedInUser == null) {
                response.sendRedirect("Login.jsp");
                return;
            }
            username = loggedInUser.getUsername();
        }

        List<Review> reviews = null;
        try {
            reviews = reviewsDao.getReviewByUsername(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("reviews", reviews);
        request.setAttribute("username", username);
        request.setAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        request.getRequestDispatcher("/ReviewsByUser.jsp").forward(request, response);
    }

}
