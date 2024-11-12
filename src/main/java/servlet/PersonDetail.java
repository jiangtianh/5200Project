package servlet;

import dal.PeopleTitlesDao;
import dal.PersonDao;
import model.PeopleTitle;
import model.Person;
import model.Title;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/persondetail")
public class PersonDetail extends HttpServlet {
    protected PersonDao personDao;
    protected PeopleTitlesDao peopleTitlesDao;

    @Override
    public void init() throws ServletException {
        personDao = PersonDao.getInstance();
        peopleTitlesDao = PeopleTitlesDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String personId = request.getParameter("personId");
        Person person = null;
        List<Title> famousTitles = null;
        try {
            person = personDao.getPersonByPersonId(personId);
            famousTitles = peopleTitlesDao.getTitlesFamousForPerson(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("person", person);
        request.setAttribute("famousTitles", famousTitles);

        request.getRequestDispatcher("/PersonDetail.jsp").forward(request, response);
    }

}
