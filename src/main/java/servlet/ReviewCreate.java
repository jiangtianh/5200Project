package servlet;

import dal.ReviewsDao;
import dal.TitlesDao;
import model.Review;
import model.Title;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/reviewcreate")
public class ReviewCreate extends HttpServlet {
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
        if (titleId == null || titleId.isEmpty()) {
            resp.sendRedirect("TitleDetail.jsp");
            return;
        }

        Title title = null;
        try {
            title = titlesDao.getTitleById(titleId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(title.getTitleId() + title.getPrimaryTitle());
        req.setAttribute("title", title);
        req.getRequestDispatcher("/ReviewCreate.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String titleId = req.getParameter("titleId");
        Title title = null;
        try {
            title = titlesDao.getTitleById(titleId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        double rating = Double.parseDouble(req.getParameter("rating"));
        String content = req.getParameter("content");

        Review review = new Review(title, loggedInUser, rating, content);

        try {
            reviewsDao.create(review);
            resp.sendRedirect("reviewsbyuser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
