package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepository {

    private static List<Course> courses;

    @PostConstruct
    public void init(){
        courses = new ArrayList<>();
        courses.add(new Course(Long.parseLong("41247"), "CS50", "intro to structured programming"));
        courses.add(new Course(Long.parseLong("25647"), "CS60", "intro to OOP"));
        courses.add(new Course(Long.parseLong("11156"), "CS70", "intro to Computer Science"));
        courses.add(new Course(Long.parseLong("16359"), "CS80", "intro to Web Design"));
        courses.add(new Course(Long.parseLong("09344"), "CS90", "intro to Web Programming"));
    }

    public List<Course> findAllCourses(){
        return courses;
    }

    public Course findById(Long courseId){
        return courses.stream().filter(i -> i.getCourseId().equals(courseId)).findFirst().orElse(null);
    }

    public List<Student> findAllStudentsByCourse(Long courseId){
        return findById(courseId).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course){
        Course c = courses.get(courses.indexOf(course));
        c.addStudent(student);
        return c;
    }
}
