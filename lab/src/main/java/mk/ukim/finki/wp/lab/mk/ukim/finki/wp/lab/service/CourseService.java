package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.enums.Type;

import java.util.List;
import java.util.Optional;

public interface CourseService{
    List<Student> listStudentsByCourse(Long courseId);
    Course addStudentInCourse(String username, Long courseId);
    List<Course> listAll();
    Course findCourseById(Long courseId);
    Optional<Course> addCourse(String name, String desc, Long teacherId, Long courseId, Type type);
    void deleteCourse(Long id);

}