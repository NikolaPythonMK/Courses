package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;

import java.util.List;

public interface EnrollmentService {

    List<Student> searchInStudent(String part);
    List<Course> searchInCourse(String part);

}
