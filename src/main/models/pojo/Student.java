package main.models.pojo;

/**
 * Created by Di on 18.04.2017.
 */
public class Student
{

    private int id;
    private String name;
    private int age;
    private int groupId;
    private StudyGroup studyGroup;


    public Student() {
    }

    public Student(int id, String name, int age, int groupId, StudyGroup studyGroup) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.groupId = groupId;
        this.studyGroup = studyGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", groupId=" + groupId +
                ", studyGroup=" + studyGroup +
                '}';
    }
}
