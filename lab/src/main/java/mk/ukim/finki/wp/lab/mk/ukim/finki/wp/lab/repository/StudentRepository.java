package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    List<Student> findAllByCoursesContaining(Course course);
    List<Student> findAllByNameContainingOrUsernameContainingOrSurnameContaining(String part, String part2, String part3);
    List<Student> findAllByNameEqualsOrSurnameEquals(String text, String text2);
}
