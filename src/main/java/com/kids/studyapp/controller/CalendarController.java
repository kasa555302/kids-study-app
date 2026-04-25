package com.kids.studyapp.controller;

import com.kids.studyapp.entity.Homework;
import com.kids.studyapp.repository.HomeworkRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final HomeworkRepository homeworkRepository;

    public CalendarController(HomeworkRepository homeworkRepository) {
        this.homeworkRepository = homeworkRepository;
    }

    @GetMapping
    public String index(@RequestParam(required = false) Integer year,
                        @RequestParam(required = false) Integer month,
                        Model model) {
        YearMonth ym = (year != null && month != null)
                ? YearMonth.of(year, month) : YearMonth.now();

        LocalDate from = ym.atDay(1);
        LocalDate to   = ym.atEndOfMonth();
        Map<LocalDate, List<Homework>> homeworkMap = homeworkRepository
                .findByDueDateBetweenOrderByDueDateAsc(from, to)
                .stream()
                .collect(Collectors.groupingBy(Homework::getDueDate));

        model.addAttribute("yearMonth", ym);
        model.addAttribute("homeworkMap", homeworkMap);
        return "calendar/index";
    }

    /** カレンダー画面からかだいを削除する */
    @PostMapping("/delete-homework/{id}")
    public String deleteHomework(@PathVariable Long id,
                                 @RequestParam int year, @RequestParam int month) {
        homeworkRepository.deleteById(id);
        return "redirect:/calendar?year=" + year + "&month=" + month;
    }
}
