package com.kids.studyapp.controller;

import com.kids.studyapp.service.StudyTimerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/timer")
public class TimerController {

    private final StudyTimerService studyTimerService;

    public TimerController(StudyTimerService studyTimerService) {
        this.studyTimerService = studyTimerService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("records", studyTimerService.findAll());
        return "timer/index";
    }

    /** JavaScript から fetch で呼ぶ JSON エンドポイント */
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> save(@RequestParam String subject,
                                                     @RequestParam int durationSeconds) {
        var record = studyTimerService.save(subject, durationSeconds);
        boolean stampEarned = durationSeconds >= 600;
        return ResponseEntity.ok(Map.of(
                "formatted", record.getFormattedDuration(),
                "stampEarned", stampEarned
        ));
    }
}
