package com.kids.studyapp.repository;

import com.kids.studyapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByScheduleDateBetweenOrderByScheduleDateAsc(LocalDate from, LocalDate to);
}
