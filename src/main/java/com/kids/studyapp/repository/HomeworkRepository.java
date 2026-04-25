package com.kids.studyapp.repository;

import com.kids.studyapp.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
    List<Homework> findAllByOrderByDueDateAsc();
}
