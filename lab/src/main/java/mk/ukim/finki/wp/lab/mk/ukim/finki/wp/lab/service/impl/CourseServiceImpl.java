package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.enums.Type;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.exception.TeacherNotFoundException;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.TeacherRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository,
                             StudentRepository studentRepository,
                             TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        return studentRepository.findAllByCoursesContaining(course);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        Student student = studentRepository.findById(username).orElseThrow();
        course.addStudent(student);
        return courseRepository.save(course);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAll(Sort.by("name").ascending());
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }

    @Override
    public Optional<Course> addCourse(String name, String desc, Long teacherId, Long courseId, Type type) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        if(courseRepository.findByName(name).isPresent() && courseId == null){
            return Optional.empty();
        }
        else if(courseId != null){
            Course course = new Course(courseId, name, desc, teacher, type);
            return Optional.of(courseRepository.save(course));
        }
        else{
            Course course = new Course(name, desc, teacher, type);
            return Optional.of(courseRepository.save(course));
        }
    }

    @Override
    public void deleteCourse(Long id) {
        Course course = courseRepository.findById(id).orElseThrow();
        courseRepository.delete(course);
    }
}
