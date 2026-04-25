package com.kids.studyapp.controller;

import com.kids.studyapp.entity.ContentOption;
import com.kids.studyapp.service.ContentOptionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/content-options")
public class ContentOptionController {

    private final ContentOptionService service;

    public ContentOptionController(ContentOptionService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("options", service.findAll());
        model.addAttribute("newOption", new ContentOption());
        return "content-option/index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ContentOption option) {
        service.save(option);
        return "redirect:/content-options";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        service.findById(id).ifPresent(o -> model.addAttribute("editOption", o));
        model.addAttribute("options", service.findAll());
        model.addAttribute("newOption", new ContentOption());
        return "content-option/index";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute ContentOption option) {
        option.setId(id);
        service.save(option);
        return "redirect:/content-options";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/content-options";
    }
}
