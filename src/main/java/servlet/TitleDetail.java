package servlet;

import dal.*;
import model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/titledetail")
public class TitleDetail extends HttpServlet {
    protected TitlesDao titlesDao;
    protected RatingsDao ratingsDao;
    protected TitleGenreDao titleGenreDao;
    protected PrincipalsDao principalsDao;
    protected TitleDirectorDao titleDirectorDao;
    protected TitleWriterDao titleWriterDao;

    @Override
    public void init() throws ServletException {
        titlesDao = TitlesDao.getInstance();
        ratingsDao = RatingsDao.getInstance();
        titleGenreDao = TitleGenreDao.getInstance();
        principalsDao = PrincipalsDao.getInstance();
        titleDirectorDao = TitleDirectorDao.getInstance();
        titleWriterDao = TitleWriterDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titleId = request.getParameter("titleId");
        Title title = null;
        Rating rating = null;
        List<Genre> genres = null;
        List<Principals> principals = null;
        List<Person> directors = null;
        List<Person> writers = null;

        try {
            title = titlesDao.getTitleById(titleId);
            rating = ratingsDao.getRatingByTitle(title);
            genres = titleGenreDao.getGenresForTitle(title);
            principals = principalsDao.getPrincipalsForTitle(title);
            principals.sort((p1, p2) -> Integer.compare(p1.getOrdering(), p2.getOrdering()));
            directors = titleDirectorDao.getDirectorsForTitle(title);
            writers = titleWriterDao.getWritersForTitle(title);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Principals> stars = new ArrayList<>();
        List<Principals> crews = new ArrayList<>();
        for (Principals principal : principals) {
            if (principal.getCategory().equals(Principals.JobCategory.actor) || principal.getCategory().equals(Principals.JobCategory.actor)) {
                String character = principal.getCharacters();
                character = "'" + character.replace("[", "").replace("]", "").replace("\"", "") + "'";
                principal.setCharacters(character);
                stars.add(principal);
            } else {
                crews.add(principal);
            }
        }


        request.setAttribute("title", title);
        request.setAttribute("rating", rating);
        request.setAttribute("genres", genres);
        request.setAttribute("directors", directors);
        request.setAttribute("writers", writers);
        request.setAttribute("stars", stars);
        request.setAttribute("crews", crews);

        request.getRequestDispatcher("/TitleDetail.jsp").forward(request, response);
    }
}
