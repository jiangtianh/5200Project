package servlet;

import model.Title;
import model.User;
import service.RecommendationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/recommendationbygenres")
public class RecommendationByGenres extends HttpServlet {
    protected RecommendationService recommendationService;

    @Override
    public void init() throws ServletException {
        recommendationService = new RecommendationService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            resp.sendRedirect("Login.jsp");
            return;
        }

        User user = (User) session.getAttribute("loggedInUser");

        int pageNumber = 1;
        int pageSize = 25;
        if (req.getParameter("pageNumber") != null) {
            pageNumber = Integer.parseInt(req.getParameter("pageNumber"));
        }

        List<Title> recommendedTitles = null;
        try {
            recommendedTitles = recommendationService.recommendBySimilarGenres(user, pageNumber, pageSize);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("recommendedTitles", recommendedTitles);
        req.setAttribute("pageNumber", pageNumber);
        req.getRequestDispatcher("/RecommendationByGenres.jsp").forward(req, resp);
    }


}
