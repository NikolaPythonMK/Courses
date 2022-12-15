package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface GradeService {
    Optional<Grade> addGrade(String username, Long courseId, Character grade, LocalDateTime timestamp);

    List<Grade> findAll();
}
