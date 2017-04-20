package main.models.pojo;

/**
 * Created by Di on 18.04.2017.
 */
public class Journal
{

    private int id;
    private int lessonId;
    private Lesson lesson;
    private int studentId;
    private Student student;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", lesson=" + lesson +
                ", studentId=" + studentId +
                ", student=" + student +
                '}';
    }
}
