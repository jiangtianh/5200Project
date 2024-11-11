package servlet;

import dal.RatingsDao;
import dal.TitleGenreDao;
import dal.TitlesDao;
import model.Genre;
import model.Rating;
import model.Title;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/titledetail")
public class TitleDetail extends HttpServlet {
    protected TitlesDao titlesDao;
    protected RatingsDao ratingsDao;
    protected TitleGenreDao titleGenreDao;

    @Override
    public void init() throws ServletException {
        titlesDao = TitlesDao.getInstance();
        ratingsDao = RatingsDao.getInstance();
        titleGenreDao = TitleGenreDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titleId = request.getParameter("titleId");
        Title title = null;
        Rating rating = null;
        List<Genre> genres = null;
        try {
            title = titlesDao.getTitleById(titleId);
            rating = ratingsDao.getRatingByTitle(title);
            genres = titleGenreDao.getGenresForTitle(title);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("title", title);
        request.setAttribute("rating", rating);
        request.setAttribute("genres", genres);
        request.getRequestDispatcher("/TitleDetail.jsp").forward(request, response);
    }
}
