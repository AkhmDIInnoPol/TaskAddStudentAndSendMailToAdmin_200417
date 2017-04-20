package main.controllers;

import main.models.pojo.Student;
import main.services.SendMailService;
import main.services.StudentServiceInterface;
import main.services.StudentsService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Di on 20.04.2017.
 */
public class AddEditStudentServlet extends HttpServlet
{

    private static StudentServiceInterface studentService = new StudentsService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students = studentService.getAllStudents();
        req.setAttribute("list", students);
        req.getRequestDispatcher("/addEditStudent.jsp").forward(req, resp);


//        getServletContext().getRequestDispatcher("/addEditStudent.jsp").forward(req,resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");

        String age = req.getParameter("age");
        String group_id = req.getParameter("group_id");
        String buttonClickedName = req.getParameter("buttonClicked");


        if (buttonClickedName.equals("addStudentClicked"))
        {
            addStudent(name, age, group_id);
        }
        if (buttonClickedName.equals("editStudentClicked"));
        {
            String studentId = req.getParameter("studentId");
            editStudent(studentId, name, age, group_id);
        }




    }



    private void editStudent(String studentId, String name, String ageStr, String group_idStr)
    {
        int id = Integer.parseInt(studentId);
        int age = Integer.parseInt(ageStr);
        int group_Id = Integer.parseInt(group_idStr);

        Student student = new Student(id, name, age, group_Id, null);

        studentService.editStudent(student);
    }


    private void addStudent(String name, String ageStr, String group_idStr)
    {
        int age = Integer.parseInt(ageStr);
        int group_Id = Integer.parseInt(group_idStr);

        Student student = new Student(0, name, age, group_Id, null);

        studentService.addStudent(student);
    }





}
