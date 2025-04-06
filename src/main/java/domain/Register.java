package domain;

import java.time.LocalDateTime;

public class Register {

    // Atributos: id(int), registerDate(LocalDateTime), studentId(String), courseId(String)

    private int id;
    private LocalDateTime registerDate;
    private String studentId;
    private String courseId;

    //Constructor
    public Register(int id, LocalDateTime registerDate, String studentId, String courseId) {
        this.id = id;
        this.registerDate = registerDate;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    //Constructor con solo el ID
    public Register(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", registerDate=" + registerDate +
                ", studentId='" + studentId + '\'' +
                ", courseId='" + courseId + '\'' +
                '}';
    }

}
