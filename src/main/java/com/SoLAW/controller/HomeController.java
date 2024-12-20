package com.SoLAW.controller;

import com.SoLAW.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private ArticlesService articlesService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articlesService.getLatestArticles(3));
        return "index";
    }
}
