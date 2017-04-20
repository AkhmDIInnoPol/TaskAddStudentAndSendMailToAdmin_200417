package main.models.pojo;

import java.sql.Timestamp;

/**
 * Created by Di on 18.04.2017.
 */
public class Lesson
{


    private int id;
    private int studyGroupId;
    private StudyGroup studyGroup;
    private Timestamp lessonDate;
    private int room;
    private String description;


    public Lesson() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudyGroupId() {
        return studyGroupId;
    }

    public void setStudyGroupId(int studyGroupId) {
        this.studyGroupId = studyGroupId;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setStudyGroup(StudyGroup studyGroup) {
        this.studyGroup = studyGroup;
    }

    public Timestamp getLessonDate() {
        return lessonDate;
    }

    public void setLessonDate(Timestamp lessonDate) {
        this.lessonDate = lessonDate;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", studyGroupId=" + studyGroupId +
                ", studyGroup=" + studyGroup +
                ", lessonDate=" + lessonDate +
                ", room=" + room +
                ", description='" + description + '\'' +
                '}';
    }
}
