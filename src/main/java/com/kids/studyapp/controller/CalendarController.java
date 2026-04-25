package com.kids.studyapp.controller;

import com.kids.studyapp.entity.Schedule;
import com.kids.studyapp.service.CalendarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping
    public String index(@RequestParam(required = false) Integer year,
                        @RequestParam(required = false) Integer month,
                        Model model) {
        YearMonth ym = (year != null && month != null)
                ? YearMonth.of(year, month) : YearMonth.now();

        Map<LocalDate, List<Schedule>> scheduleMap = calendarService.getMonthlySchedules(ym);
        model.addAttribute("yearMonth", ym);
        model.addAttribute("scheduleMap", scheduleMap);
        model.addAttribute("newSchedule", new Schedule());
        return "calendar/index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Schedule schedule) {
        calendarService.save(schedule);
        YearMonth ym = YearMonth.from(schedule.getScheduleDate());
        return "redirect:/calendar?year=" + ym.getYear() + "&month=" + ym.getMonthValue();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam int year, @RequestParam int month) {
        calendarService.delete(id);
        return "redirect:/calendar?year=" + year + "&month=" + month;
    }
}
