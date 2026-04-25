package com.kids.studyapp.controller;

import com.kids.studyapp.entity.Homework;
import com.kids.studyapp.service.ContentOptionService;
import com.kids.studyapp.service.HomeworkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ContentOptionService contentOptionService;

    public HomeworkController(HomeworkService homeworkService, ContentOptionService contentOptionService) {
        this.homeworkService = homeworkService;
        this.contentOptionService = contentOptionService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("homeworks", homeworkService.findAll());
        model.addAttribute("newHomework", new Homework());
        model.addAttribute("contentOptions", contentOptionService.findAll());
        return "homework/index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Homework homework) {
        homeworkService.save(homework);
        return "redirect:/homework";
    }

    @PostMapping("/toggle/{id}")
    public String toggle(@PathVariable Long id) {
        homeworkService.toggleDone(id);
        return "redirect:/homework";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        homeworkService.delete(id);
        return "redirect:/homework";
    }
}
