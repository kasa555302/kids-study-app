package com.kids.studyapp.service;

import com.kids.studyapp.entity.Schedule;
import com.kids.studyapp.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class CalendarService {

    private final ScheduleRepository scheduleRepository;

    public CalendarService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public Map<LocalDate, List<Schedule>> getMonthlySchedules(YearMonth yearMonth) {
        LocalDate from = yearMonth.atDay(1);
        LocalDate to = yearMonth.atEndOfMonth();
        List<Schedule> list = scheduleRepository.findByScheduleDateBetweenOrderByScheduleDateAsc(from, to);
        return list.stream().collect(Collectors.groupingBy(Schedule::getScheduleDate));
    }

    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    public void update(Long id, Schedule form) {
        scheduleRepository.findById(id).ifPresent(s -> {
            s.setScheduleDate(form.getScheduleDate());
            s.setTitle(form.getTitle());
            s.setMemo(form.getMemo());
            scheduleRepository.save(s);
        });
    }

    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
