package main.models.dao;

import main.models.connection.ConnectorDB;
import main.models.pojo.Student;
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
public class StudentDAOImpl implements StudentDAO<Student>
{

    static {
        PropertyConfigurator.configure("log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(StudyGroupDAOImpl.class);



    private Connection connection;
    private StudyGroupDAOImpl studyGroupConnector;

    public StudentDAOImpl()
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



    public ArrayList<Student> getAll() {

        ArrayList<Student> students = new ArrayList<Student>();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.student");



            while (result.next())
            {
                Student student = new Student();

                student.setId(result.getInt("id"));
                student.setName(result.getString("name"));
                student.setAge(result.getInt("age"));
                student.setGroupId(result.getInt("group_id"));
                int groupId = student.getGroupId();
                student.setStudyGroup(studyGroupConnector.getById(groupId));

                students.add(student);
            }


        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return students;
    }


    public Student getById(int id) {

        Student student = new Student();

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from public.student " +
                    "WHERE id = " + id);

            result.next();



            student.setId(result.getInt("id"));
            student.setName(result.getString("name"));
            student.setAge(result.getInt("age"));
            student.setGroupId(result.getInt("group_id"));
            int groupId = student.getGroupId();
            student.setStudyGroup(studyGroupConnector.getById(groupId));

        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

        return student;
    }



    public void insert(Student student) {

        int id = student.getId();

        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from public.student "
                    + "WHERE id = " + id);




            boolean isContainRow = resultSet.next();

            if (!isContainRow)
            {
                if (id != 0)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement
                            ("INSERT INTO student(id, name, age, group_id) \n"
                                    + "VALUES (?,?,?,?)");


                    preparedStatement.setInt(1, student.getId());
                    preparedStatement.setString(2,student.getName());
                    preparedStatement.setInt(3, student.getAge());
                    preparedStatement.setInt(4, student.getGroupId());

                    StudyGroup studyGroup = student.getStudyGroup();
                    studyGroupConnector.insert(studyGroup);

                    preparedStatement.executeUpdate();
                }

                if (id == 0)
                {
                    PreparedStatement preparedStatement = connection.prepareStatement
                            ("INSERT INTO student( name, age, group_id) \n"
                                    + "VALUES (?,?,?)");



                    preparedStatement.setString(1,student.getName());
                    preparedStatement.setInt(2, student.getAge());
                    preparedStatement.setInt(3, student.getGroupId());

                    StudyGroup studyGroup = student.getStudyGroup();
                    studyGroupConnector.insert(studyGroup);

                    preparedStatement.executeUpdate();
                }
            }
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }

    }

    public void update(Student student) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("UPDATE student SET  name = ?, age = ?, " +
                            "group_id = ? WHERE id = ?");

            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2,student.getAge());
            preparedStatement.setInt(3,student.getGroupId());
            preparedStatement.setInt(4,student.getId());
            StudyGroup studyGroup = student.getStudyGroup();
            studyGroupConnector.update(studyGroup);




            preparedStatement.executeUpdate();
        }
        catch (SQLException ex)
        {
            logger.error(ex.getMessage());
        }
    }

    public void delete(Student student) {
        int id = student.getId();

        try {
            Statement statement = connection.createStatement();

            String query = "DELETE FROM student\n" +
                    "WHERE id = " + id;
            statement.executeUpdate(query);
//            StudyGroup studyGroup = student.getStudyGroup();
//            studyGroupConnector.delete(studyGroup);
        }
        catch (SQLException ex)
        {
            logger.error(ex);
        }
    }

}
