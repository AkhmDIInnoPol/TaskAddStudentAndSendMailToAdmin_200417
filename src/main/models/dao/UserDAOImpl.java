package main.models.dao;

import main.models.connection.ConnectorDB;
import main.models.pojo.StudyGroup;
import main.models.pojo.User;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Di on 20.04.2017.
 */
public class UserDAOImpl implements UserDAO<User>
{
    static {
        PropertyConfigurator.configure("./src/main/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);
    private Connection connection;

    public UserDAOImpl()
    {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            connection = connectorDB.getConnection();

        }
        catch (IOException ex)
        {
            logger.error(ex.getMessage());
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
        catch (PropertyVetoException ex)
        {
            logger.error(ex.getMessage());
        }
    }


    public User findUserByLoginAndPassword(String login, String password) {

        User user = new User();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.lesson " +
                    "WHERE id = " + login + " AND " + "password = " + password);

            result.next();



            user.setId(result.getInt("id"));
            user.setLogin(result.getString("login"));
            user.setPassword(result.getString("password"));

        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        logger.debug("User " + user);

        return user;
    }

    public ArrayList<User> getAll() {
        throw new NotImplementedException();
    }

    public User getById(int id) {
        throw new NotImplementedException();
    }

    public void update(User object) {
        throw new NotImplementedException();
    }

    public void insert(User object) {
        throw new NotImplementedException();
    }

    public void delete(User object) {
        throw new NotImplementedException();
    }
}
