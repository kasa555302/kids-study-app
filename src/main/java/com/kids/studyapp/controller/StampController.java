package com.kids.studyapp.controller;

import com.kids.studyapp.service.StampService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stamps")
public class StampController {

    private final StampService stampService;

    public StampController(StampService stampService) {
        this.stampService = stampService;
    }

    @GetMapping
    public String index(Model model) {
        var stamps = stampService.findAll();
        long total = stampService.count();

        String reward = "";
        if (total >= 30) reward = "🏆 すごい！なんでも1つ好きなことをしていいよ！";
        else if (total >= 20) reward = "🎂 ケーキを食べよう！";
        else if (total >= 10) reward = "🍦 アイスを食べよう！";
        else if (total >= 5)  reward = "🌟 シールを1枚もらえるよ！";

        model.addAttribute("stamps", stamps);
        model.addAttribute("total", total);
        model.addAttribute("reward", reward);
        return "stamp/index";
    }
}
