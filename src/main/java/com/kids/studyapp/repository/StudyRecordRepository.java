package com.kids.studyapp.repository;

import com.kids.studyapp.entity.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
    List<StudyRecord> findAllByOrderByStudyDateDesc();
}
