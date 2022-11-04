package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        if(courseId == null){
            return null;
        }
        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        if(username == null || courseId == null) return null;

        Student student = studentRepository.findAllStudents().stream()
                .filter(i -> i.getUsername().equals(username)).findFirst().orElse(null);
        Course course = courseRepository.findById(courseId);

        if(student != null && course != null){
            return courseRepository.addStudentToCourse(student, course);
        }
        return null;
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId);
    }
}
