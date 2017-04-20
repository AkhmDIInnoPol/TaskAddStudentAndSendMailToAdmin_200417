package main.models.dao;

import main.models.connection.ConnectorDB;
import main.models.pojo.Lesson;
import main.models.pojo.StudyGroup;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Di on 18.04.2017.
 */
public class LessonDAOImpl implements LessonDao<Lesson>
{


    private static final Logger logger = Logger.getLogger(LessonDAOImpl.class);



    private Connection connection;
    private StudyGroupDAOImpl studyGroupConnector;


    public LessonDAOImpl()
    {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            connection = connectorDB.getConnection();
            studyGroupConnector = new StudyGroupDAOImpl();
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




    public ArrayList<Lesson> getAll() {

        ArrayList<Lesson> lessons = new ArrayList<Lesson>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.lesson");



            while (result.next())
            {
                Lesson lesson = new Lesson();

                lesson.setId(result.getInt("id"));
                lesson.setStudyGroupId(result.getInt("study_group_id"));
                int groupId = lesson.getStudyGroupId();
                lesson.setStudyGroup(studyGroupConnector.getById(groupId));
                lesson.setLessonDate(result.getTimestamp("lesson_date"));
                lesson.setRoom(result.getInt("room"));
                lesson.setDescription(result.getString("desription"));

                lessons.add(lesson);
            }


        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return lessons;
    }




    public Lesson getById(int id) {

        Lesson lesson = new Lesson();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.lesson " +
                    "WHERE id = " + id);

            result.next();



            lesson.setId(result.getInt("id"));
            lesson.setStudyGroupId(result.getInt("study_group_id"));
            int groupId = lesson.getStudyGroupId();
            lesson.setStudyGroup(studyGroupConnector.getById(groupId));
            lesson.setLessonDate(result.getTimestamp("lesson_date"));
            lesson.setRoom(result.getInt("group_id"));
            lesson.setDescription(result.getString("description"));
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return lesson;
    }



    public void insert(Lesson lesson) {

        int id = lesson.getId();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.lesson "
                    + "WHERE id = " + id);




            boolean isContainRow = resultSet.next();

            if (!isContainRow)
            {

                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO lesson(id, study_group_id, lesson_date, room, description) \n"
                                + "VALUES (?,?,?,?,?)");


                preparedStatement.setInt(1, lesson.getId());
                preparedStatement.setInt(2, lesson.getStudyGroupId());
                StudyGroup studyGroup = lesson.getStudyGroup();
                studyGroupConnector.insert(studyGroup);
                preparedStatement.setTimestamp(3, lesson.getLessonDate());
                preparedStatement.setInt(4, lesson.getRoom());



                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }


    public void update(Lesson lesson) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE lesson SET  study_group_id = ?, lesson_date = ?, " +
                            "room = ?, description = ? WHERE id = ?");

            preparedStatement.setInt(1, lesson.getStudyGroupId());
            StudyGroup studyGroup = lesson.getStudyGroup();
            studyGroupConnector.update(studyGroup);
            preparedStatement.setTimestamp(2,lesson.getLessonDate());
            preparedStatement.setInt(3,lesson.getRoom());
            preparedStatement.setString(4, lesson.getDescription());
            preparedStatement.setInt(5, lesson.getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }

    public void delete(Lesson lesson) {
        int id = lesson.getId();

        try {
            Statement statement = connection.createStatement();

            String query = "DELETE FROM lesson\n" +
                    "WHERE id = " + id;
            statement.executeUpdate(query);
//            StudyGroup studyGroup = lesson.getStudyGroup();
//            studyGroupConnector.delete(studyGroup);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }
    }

}
