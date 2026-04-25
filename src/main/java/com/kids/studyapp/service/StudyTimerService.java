package com.kids.studyapp.service;

import com.kids.studyapp.entity.Stamp;
import com.kids.studyapp.entity.StudyRecord;
import com.kids.studyapp.repository.StampRepository;
import com.kids.studyapp.repository.StudyRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class StudyTimerService {

    private final StudyRecordRepository studyRecordRepository;
    private final StampRepository stampRepository;

    public StudyTimerService(StudyRecordRepository studyRecordRepository, StampRepository stampRepository) {
        this.studyRecordRepository = studyRecordRepository;
        this.stampRepository = stampRepository;
    }

    public List<StudyRecord> findAll() {
        return studyRecordRepository.findAllByOrderByStudyDateDesc();
    }

    /** タイマー停止時に記録保存。10分以上でスタンプ自動付与 */
    public StudyRecord save(String subject, int durationSeconds) {
        StudyRecord record = new StudyRecord();
        record.setSubject(subject);
        record.setDurationSeconds(durationSeconds);
        record.setStudyDate(LocalDate.now());
        studyRecordRepository.save(record);

        if (durationSeconds >= 600) {
            Stamp stamp = new Stamp();
            stamp.setEarnedDate(LocalDate.now());
            stamp.setStampEmoji("⭐");
            stamp.setReason(subject + "を" + record.getFormattedDuration() + "勉強した！");
            stampRepository.save(stamp);
        }
        return record;
    }
}
