package main.models.dao;



/**
 * Created by Di on 20.04.2017.
 */
public interface UserDAO<User> extends DAO<User>
{
    User findUserByLoginAndPassword(String login, String password);



}
