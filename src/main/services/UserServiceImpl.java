package main.services;

import main.controllers.LoginServlet;
import main.models.dao.UserDAO;
import main.models.dao.UserDAOImpl;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by Di on 20.04.2017.
 */
public class UserServiceImpl implements UserService
{

    static {
        PropertyConfigurator.configure("log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(LoginServlet.class);


    private static UserDAO userDAO = new UserDAOImpl();

    public User auth(String login, String password) {
        User user = (User) userDAO.findUserByLoginAndPassword(login, password);

        logger.debug("user + " + user);

        if (user != null && user.isBlocked())
        {
            return null;
        }

        logger.debug("user not blocked");

        return user;
    }
}
