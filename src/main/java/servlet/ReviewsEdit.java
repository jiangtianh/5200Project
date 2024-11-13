package servlet;

import dal.ReviewsDao;
import dal.UsersDao;
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

@WebServlet("/reviewsedit")
public class ReviewsEdit extends HttpServlet {
    protected ReviewsDao reviewsDao;
    protected UsersDao usersDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
        usersDao = UsersDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));

        Review review = null;
        try {
            review = reviewsDao.getReviewById(reviewId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (review == null || !review.getUser().equals(loggedInUser)) {
            resp.sendRedirect("index.jsp");
            return;
        }

        req.setAttribute("review", review);
        req.setAttribute("loggedInUser", loggedInUser);
        req.getRequestDispatcher("/ReviewsEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        int reviewId = Integer.parseInt(req.getParameter("reviewId"));
        String content = req.getParameter("content");
        double newRating = Double.parseDouble(req.getParameter("rating"));

        Review review = null;
        try {
            review = reviewsDao.getReviewById(reviewId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (review == null || !review.getUser().equals(loggedInUser)) {
            resp.sendRedirect("index.jsp");
            return;
        }

        review.setContent(content);
        try {
            reviewsDao.update(review);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
