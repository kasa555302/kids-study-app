package com.kids.studyapp.repository;

import com.kids.studyapp.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAllByOrderByDueDateAsc();
    List<Homework> findByDueDateBetweenOrderByDueDateAsc(LocalDate from, LocalDate to);
}
