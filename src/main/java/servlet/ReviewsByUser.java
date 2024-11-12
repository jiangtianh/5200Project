package servlet;

import dal.ReviewsDao;
import model.Review;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ReviewsByUser extends HttpServlet {

    protected ReviewsDao reviewsDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("username");
        List<Review> reviews = null;
        try {
            reviews = reviewsDao.getReviewByUsername(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("reviews", reviews);

        request.getRequestDispatcher("/ReviewsByUser.jsp").forward(request, response);
    }

}
