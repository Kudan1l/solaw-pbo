package com.SoLAW.controller;

import com.SoLAW.model.Articles;
import com.SoLAW.service.ArticlesService;
import com.SoLAW.service.CategoriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticlesService articlesService;

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping
    public String showIndex(Model model) {
        model.addAttribute("countArticle", articlesService.getTotalArticles());
        model.addAttribute("countPublish", articlesService.getPublishedArticles());
        model.addAttribute("countDraft", articlesService.getDraftArticles());
        return "dashboard/admin/index";
    }

    @GetMapping("/article")
    public String showArticle(Model model) {
        model.addAttribute("articles", articlesService.getAllArticles());
        return "dashboard/admin/article/view";
    }

    @GetMapping("/article/create")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new Articles());
        model.addAttribute("categories", categoriesService.getAllCategories());
        return "dashboard/admin/article/add";
    }

    @PostMapping("/article/store")
    public String createArticle(@Valid Articles articles, @RequestParam(required = false) List<Integer> categoryId, BindingResult result) {
        if (result.hasErrors()) {
            return "/admin/article/create";
        }

        articlesService.store(articles, categoryId);
        return "redirect:/admin/article";
    }

    @GetMapping("/article/edit/{id}")
    public String editArticle(@PathVariable Integer id, Model model) {
        Articles articles = articlesService.getArticleById(id);

        model.addAttribute("articles", articles);
        model.addAttribute("categories", categoriesService.getAllCategories());

        return "dashboard/admin/article/edit";
    }

    @PostMapping("/article/update/{id}")
    public String updateArticle(@PathVariable Integer id, @Valid Articles articles,
                                @RequestParam(required = false) List<Integer> categoryId, BindingResult result){
        if (result.hasErrors()) {
            return "/admin/article";
        }

        articlesService.update(id, articles, categoryId);
        return "redirect:/admin/article";
    }

    @DeleteMapping("/article/{id}")
    public String deleteArticle(@PathVariable Integer id) {
        articlesService.deleteArticle(id);
        return "redirect:/admin/article";
    }
}
