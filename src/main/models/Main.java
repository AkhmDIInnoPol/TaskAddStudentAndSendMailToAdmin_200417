package main.models;

import main.models.dao.LessonDAOImpl;
import main.models.dao.StudentDAOImpl;
import main.models.pojo.Lesson;
import main.models.pojo.Student;
import main.models.pojo.StudyGroup;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Di on 18.04.2017.
 */
public class Main
{
    static {
        PropertyConfigurator.configure("./src/main/logger/log4j.properties");
    }
    private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        StudentDAOImpl studentConnector = new StudentDAOImpl();


        Student student = new Student();

        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setId(1);
        studyGroup.setName("STC05");
//        student.setStudyGroup(studyGroup);
//
//        student.setId(3);
//        student.setName("Sokolov");
//        student.setAge(27);
//        student.setGroupId(1);
//        studentDAO.update(student);
//        System.out.println(student);

        Lesson lesson = new Lesson();
        lesson.setId(2);
        lesson.setStudyGroupId(1);
        lesson.setStudyGroup(studyGroup);

        int year = 2017;
        int month = 4;
        int dayOfMonth = 21;
        int hourOfDay = 8;
        int minute = 7;
        int second = 6;

        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, dayOfMonth, hourOfDay, minute, second);
        Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

        lesson.setLessonDate(timestamp);
        lesson.setRoom(504);
        lesson.setDescription("Spring");
        LessonDAOImpl lessonConnector = new LessonDAOImpl();
        lessonConnector.update(lesson);


//        JournalDAOImpl journalConnector = new JournalDAOImpl();
//        List<Journal> journals = journalConnector.getAll();
//        for (Journal journal:journals
//             ) {
//            System.out.println(journal);
//        }
//        Journal journal =
    }
}
