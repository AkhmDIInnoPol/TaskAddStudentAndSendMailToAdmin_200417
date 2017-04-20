package main.services;

import main.models.dao.DAO;
import main.models.dao.StudentDAO;
import main.models.dao.StudentDAOImpl;
import main.models.pojo.Student;

import java.util.List;

/**
 * Created by Di on 19.04.2017.
 */
public class StudentsService implements StudentServiceInterface
{

    public static StudentDAO<Student> studentDAO = new StudentDAOImpl();

    public List<Student> getAllStudents()
    {
        return studentDAO.getAll();
    }

    public void addStudent(String name, String age, String groupId) {

        int ageInt = Integer.parseInt(age);
        int groupIdInt = Integer.parseInt(groupId);
        Student student = new Student();


        studentDAO.insert(student);
    }
}
