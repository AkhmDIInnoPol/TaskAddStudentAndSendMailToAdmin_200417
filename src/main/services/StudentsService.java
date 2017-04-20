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

    public void addStudent(Student student) {

        studentDAO.insert(student);
    }


    public void editStudent(Student student)
    {
        studentDAO.update(student);
    }

}
