package main.models.dao;

import main.models.connection.ConnectorDB;
import main.models.pojo.StudyGroup;
import org.apache.log4j.PropertyConfigurator;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;

/**
 * Created by Di on 18.04.2017.
 */
public class StudyGroupDAOImpl implements StudyGroupDAO<StudyGroup>
{


    private static final Logger logger = Logger.getLogger(StudyGroupDAOImpl.class);




    private Connection connection;

    public StudyGroupDAOImpl()
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


    public ArrayList<StudyGroup> getAll() {

        ArrayList<StudyGroup> studyGroups = new ArrayList<StudyGroup>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.study_group");



            while (result.next())
            {
                StudyGroup studyGroup = new StudyGroup();

                studyGroup.setId(result.getInt("id"));
                studyGroup.setName(result.getString("name"));

                studyGroups.add(studyGroup);
            }


        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return studyGroups;
    }

    public StudyGroup getById(int id) {

        StudyGroup studyGroup = new StudyGroup();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.study_group " +
                                                            "WHERE id = " + id);

            result.next();



            studyGroup.setId(result.getInt("id"));
            studyGroup.setName(result.getString("name"));

        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return studyGroup;
    }






    public void insert(StudyGroup object) {

        int id = object.getId();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.study_group "
                    + "WHERE id = " + id);




            boolean isContainRow = resultSet.next();

            if (!isContainRow)
            {

                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO study_group(id, name) \n"
                                + "VALUES (?,?)");


                preparedStatement.setInt(1, object.getId());
                preparedStatement.setString(2,object.getName());

                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

    }

    public void update(StudyGroup object) {



        try{
            int id = object.getId();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.study_group "
                    + "WHERE id = " + id);



            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE study_group SET  name = ? WHERE id = ?");

            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2,object.getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }



    public void delete(StudyGroup object) {
        int id = object.getId();

        try {
            Statement statement = connection.createStatement();

            String query = "DELETE FROM study_group\n" +
                                "WHERE id = " + id;
            statement.executeUpdate(query);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }
    }
}
