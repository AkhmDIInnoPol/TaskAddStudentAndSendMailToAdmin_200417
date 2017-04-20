package main.controllers;

import main.models.pojo.Student;
import main.models.pojo.StudyGroup;
import main.services.StudentServiceInterface;
import main.services.StudentsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Di on 19.04.2017.
 */
public class ListController extends HttpServlet
{

    private static StudentServiceInterface studentService = new StudentsService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("value", "Hello Student!");

//        List<Student> list = new ArrayList<Student>();
//        StudyGroup studyGroup = new StudyGroup();
//        studyGroup.setId(1);
//        studyGroup.setName("STC02");
//        Student student = new Student(1, "Vasya", 18,1,studyGroup);
//        list.add(student);

        req.setAttribute("list", studentService.getAllStudents());
        getServletContext().getRequestDispatcher("/listStudents.jsp").forward(req,resp);
    }


}
