package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.CourseRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.GradeRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public GradeServiceImpl(GradeRepository gradeRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<Grade> addGrade(String username, Long courseId, Character gradeChar, LocalDateTime timestamp) {
        Student student = studentRepository.findById(username).orElseThrow();
        Course course = courseRepository.findById(courseId).orElseThrow();
        Grade grade = new Grade(gradeChar, student, course, timestamp);
        return Optional.of(gradeRepository.save(grade));
    }

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }
}
