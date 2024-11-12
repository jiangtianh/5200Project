package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import dal.TitlesDao;

import java.sql.SQLException;
import java.util.List;
import model.Title;


@WebServlet("/findtitle")
public class TitleFind extends HttpServlet {
    protected TitlesDao titlesDao;

    @Override
    public void init() throws ServletException {
        titlesDao = TitlesDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        int page = Integer.parseInt(request.getParameter("page"));
        int pageSize = 10; // default page size

        TitlesDao titlesDao = TitlesDao.getInstance();
        List<Title> titles = null;

        try {
            titles = titlesDao.getTitleByPrimaryTitle(title, page, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("titles", titles);
        request.setAttribute("currentPage", page);
        request.setAttribute("pageSize", pageSize);
        request.getRequestDispatcher("/TitleFind.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
