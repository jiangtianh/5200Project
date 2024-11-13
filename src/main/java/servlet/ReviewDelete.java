package servlet;

import dal.ReviewsDao;
import model.Review;
import model.User;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reviewdelete")
public class ReviewDelete extends HttpServlet {
    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
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

        if (review == null || !review.getUser().getUsername().equals(loggedInUser.getUsername())) {
            System.out.println("Username and LoggedInUser are not the same");
            resp.sendRedirect("index.jsp");
            return;
        }
        try {
            reviewsDao.delete(review);
            resp.sendRedirect("reviewsbyuser");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



}
