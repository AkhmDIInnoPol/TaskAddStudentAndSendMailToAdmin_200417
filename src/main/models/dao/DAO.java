package main.models.dao;

import java.util.ArrayList;

/**
 * CConnector interface for realize connection of every object.
 */
public interface DAO<T>
{

    ArrayList<T> getAll();

    T getById(int id);



     void update(T object);

    void insert(T object);

    void delete(T object);
}
