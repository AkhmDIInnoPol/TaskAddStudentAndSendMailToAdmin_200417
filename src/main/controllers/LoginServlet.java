package main.controllers;

import main.models.dao.LessonDAOImpl;
import main.services.UserService;
import main.services.UserServiceImpl;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Di on 19.04.2017.
 */
public class LoginServlet extends HttpServlet
{

    static {
        PropertyConfigurator.configure("log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(LoginServlet.class);


    private static UserService userService = new UserServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("password");

        if (userService.auth(login, pass) != null)
        {
            req.getSession().setAttribute("userLogin", login);
            logger.debug("userLogin" + login);
            resp.sendRedirect(req.getContextPath() + "/listStudents");

        }

    }
}
