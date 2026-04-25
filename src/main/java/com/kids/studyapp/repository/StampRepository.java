package com.kids.studyapp.repository;

import com.kids.studyapp.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StampRepository extends JpaRepository<Stamp, Long> {
    List<Stamp> findAllByOrderByEarnedDateDesc();
}
