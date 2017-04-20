package main.services;

import main.models.pojo.Student;

import java.util.List;

/**
 * Created by Di on 19.04.2017.
 */
public interface StudentServiceInterface
{

    List<Student> getAllStudents();

    void addStudent(String name, String age, String groupId);



}
