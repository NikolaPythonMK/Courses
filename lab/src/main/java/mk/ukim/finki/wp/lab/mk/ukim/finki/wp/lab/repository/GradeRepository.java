package mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.mk.ukim.finki.wp.lab.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    List<Grade> findAllByTimestampAfterAndTimestampBefore(LocalDateTime after, LocalDateTime before);
}
