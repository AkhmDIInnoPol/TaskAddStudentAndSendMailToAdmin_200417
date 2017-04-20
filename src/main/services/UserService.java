package main.services;

import main.models.pojo.User;

/**
 * Created by Di on 20.04.2017.
 */
public interface UserService
{

    User auth(String login, String password);


}
