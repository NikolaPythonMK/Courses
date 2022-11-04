package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Course {
    private Long courseId;
    private String name;
    private String description;
    private List<Student> students;

    public Course(Long courseId, String name, String description) {
        this.courseId = courseId;
        this.name = name;
        this.description = description;
        students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }
}
