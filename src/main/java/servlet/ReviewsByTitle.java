package servlet;

import dal.ReviewsDao;
import dal.TitlesDao;
import model.Review;
import model.Title;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/reviewsbytitle")
public class ReviewsByTitle extends HttpServlet {

    protected ReviewsDao reviewsDao;
    protected TitlesDao titlesDao;

    @Override
    public void init() throws ServletException {
        reviewsDao = ReviewsDao.getInstance();
        titlesDao = TitlesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        String titleId = req.getParameter("titleId");
        if (titleId == null || titleId.trim().isEmpty()) {
            resp.sendRedirect("index.jsp");
            return;
        }

        Title title = null;
        List<Review> reviews = null;
        try {
            title = titlesDao.getTitleById(titleId);
            reviews = reviewsDao.getReviewsByTitle(new Title(titleId));
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.setAttribute("reviews", reviews);
        req.setAttribute("title", title);
        req.setAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        req.getRequestDispatcher("/ReviewsByTitle.jsp").forward(req, resp);
    }
}
