package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dal.PersonDao;
import model.Person;

import java.io.IOException;
import java.util.List;

@WebServlet("/personfind")
public class PersonFind extends HttpServlet {
    protected PersonDao personDao;

    @Override
    public void init() throws ServletException {
        personDao = PersonDao.getInstance();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        int page = Integer.parseInt(req.getParameter("page"));
        List<Person> people = null;
        int pageSize = 10; // default page size

        try {
            people = personDao.getPersonsByName(name, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("people", people);
        req.setAttribute("currentPage", page);
        req.setAttribute("pageSize", pageSize);
        req.getRequestDispatcher("/PersonFind.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
