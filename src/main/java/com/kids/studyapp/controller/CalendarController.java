package com.kids.studyapp.controller;

import com.kids.studyapp.entity.Homework;
import com.kids.studyapp.repository.ContentOptionRepository;
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
    private final ContentOptionRepository contentOptionRepository;

    public CalendarController(HomeworkRepository homeworkRepository,
                              ContentOptionRepository contentOptionRepository) {
        this.homeworkRepository      = homeworkRepository;
        this.contentOptionRepository = contentOptionRepository;
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
        model.addAttribute("contentOptions", contentOptionRepository.findAllByOrderByIdAsc());
        return "calendar/index";
    }

    /** 完了・未完了を切り替える */
    @PostMapping("/toggle-homework/{id}")
    public String toggleHomework(@PathVariable Long id,
                                 @RequestParam int year, @RequestParam int month) {
        homeworkRepository.findById(id).ifPresent(hw -> {
            hw.setDone(!hw.isDone());
            homeworkRepository.save(hw);
        });
        return "redirect:/calendar?year=" + year + "&month=" + month;
    }

    /** かもく・ないよう・日付を編集する */
    @PostMapping("/edit-homework/{id}")
    public String editHomework(@PathVariable Long id,
                               @RequestParam String subject,
                               @RequestParam String content,
                               @RequestParam LocalDate dueDate,
                               @RequestParam int year, @RequestParam int month) {
        homeworkRepository.findById(id).ifPresent(hw -> {
            hw.setSubject(subject);
            hw.setContent(content);
            hw.setDueDate(dueDate);
            homeworkRepository.save(hw);
        });
        return "redirect:/calendar?year=" + year + "&month=" + month;
    }

    /** カレンダー画面からかだいを削除する */
    @PostMapping("/delete-homework/{id}")
    public String deleteHomework(@PathVariable Long id,
                                 @RequestParam int year, @RequestParam int month) {
        homeworkRepository.deleteById(id);
        return "redirect:/calendar?year=" + year + "&month=" + month;
    }
}
