package com.SoLAW.controller;

import com.SoLAW.model.Articles;
import com.SoLAW.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class HomeController {

    @Autowired
    private ArticlesService articlesService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("articles", articlesService.getLatestArticles(3));
        return "index";
    }

    @GetMapping("/artikel")
    public String articleMenu(Model model) {
        model.addAttribute("articles", articlesService.getAllArticles());
        return "article/article_menu";
    }
<<<<<<< HEAD
}
=======

    @GetMapping("/artikel/{id}")
    public String articleDetail(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("article", articlesService.getArticleById(id));
        model.addAttribute("formattedDate", articlesService.formatDate(articlesService.getArticleById(id).getCreatedAt()));
        return "article/article_detail";
    }
}
>>>>>>> 5ac275e3fdd44dcef90f14ddf46396476e9170a9
