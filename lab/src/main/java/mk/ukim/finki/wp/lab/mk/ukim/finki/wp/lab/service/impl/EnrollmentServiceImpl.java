package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.EnrollmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public EnrollmentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> searchInStudent(String part) {
        return studentRepository.findAllByNameContainingOrUsernameContainingOrSurnameContaining(part, part, part);
    }

    @Override
    public List<Course> searchInCourse(String part) {
        return courseRepository.findAllByNameContainingOrDescriptionContaining(part, part);
    }
}
