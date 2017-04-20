package main.models.dao;

import main.models.connection.ConnectorDB;
import main.models.pojo.Journal;
import main.models.pojo.Lesson;
import main.models.pojo.Student;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Di on 18.04.2017.
 */
public class JournalDAOImpl implements JournalDAO<Journal>
{
    static {
        PropertyConfigurator.configure("./src/main/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(LessonDAOImpl.class);



    private Connection connection;
    private StudentDAOImpl studentConnector;
    private LessonDAOImpl lessonConnector;

    public JournalDAOImpl()
    {
        try {
            ConnectorDB connectorDB = ConnectorDB.getInstance();
            connection = connectorDB.getConnection();
            studentConnector = new StudentDAOImpl();
            lessonConnector = new LessonDAOImpl();
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


    public ArrayList<Journal> getAll() {

        ArrayList<Journal> journals = new ArrayList<Journal>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.journal");



            while (result.next())
            {
                Journal journal = new Journal();

                journal.setId(result.getInt("id"));
                journal.setLessonId(result.getInt("lesson_id"));
                int lessonId = journal.getLessonId();
                journal.setLesson(lessonConnector.getById(lessonId));
                journal.setStudentId(result.getInt("student_id"));
                int studentId = journal.getStudentId();
                journal.setStudent(studentConnector.getById(studentId));

                journals.add(journal);
            }


        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return journals;
    }

    public Journal getById(int id) {

        Journal journal = new Journal();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.journal " +
                    "WHERE id = " + id);

            result.next();



            journal.setId(result.getInt("id"));
            journal.setLessonId(result.getInt("lesson_id"));
            int lessonId = journal.getLessonId();
            journal.setLesson(lessonConnector.getById(lessonId));
            journal.setStudentId(result.getInt("student_id"));
            int studentId = journal.getStudentId();
            journal.setStudent(studentConnector.getById(studentId));

        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return journal;
    }



    public void insert(Journal journal) {

        int id = journal.getId();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.journal "
                    + "WHERE id = " + id);




            boolean isContainRow = resultSet.next();

            if (!isContainRow)
            {

                PreparedStatement preparedStatement = connection.prepareStatement
                        ("INSERT INTO journal(id, lesson_id, student_id) \n"
                                + "VALUES (?,?,?)");


                preparedStatement.setInt(1, journal.getId());
                preparedStatement.setInt(2,journal.getLessonId());
                lessonConnector.insert(journal.getLesson());
                preparedStatement.setInt(3, journal.getStudentId());
                studentConnector.insert(journal.getStudent());


                preparedStatement.executeUpdate();
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

    }


    public void update(Journal journal) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE journal SET  lesson_id = ?, student_id = ?, " +
                            " WHERE id = ?");

            preparedStatement.setInt(1, journal.getLessonId());
            preparedStatement.setInt(2,journal.getStudentId());
            Lesson lesson = journal.getLesson();
            lessonConnector.update(lesson);
            Student student = journal.getStudent();
            studentConnector.update(student);

            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }

    public void delete(Journal journal) {
        int id = journal.getId();

        try {
            Statement statement = connection.createStatement();

            String query = "DELETE FROM journal\n" +
                    "WHERE id = " + id;
            statement.executeUpdate(query);

//            Lesson lesson = journal.getLesson();
//            lessonConnector.delete(lesson);
//
//            Student student = journal.getStudent();
//            studentDAO.delete(student);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }
    }


}
