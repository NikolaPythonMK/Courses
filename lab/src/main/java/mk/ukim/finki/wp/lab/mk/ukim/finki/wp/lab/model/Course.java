package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model;

import lombok.Data;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.enums.Type;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Student> students;
    @ManyToOne
    private Teacher teacher;
    @Enumerated(EnumType.STRING)
    private Type type;

    public Course(String name, String description, Teacher teacher, Type type) {
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        students = new ArrayList<>();
    }

    public Course(Long id, String name, String description, Teacher teacher, Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
        this.type = type;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Course() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
