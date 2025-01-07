package com.SoLAW.service;

import com.SoLAW.model.Articles;
import com.SoLAW.model.Categories;
import com.SoLAW.repository.ArticlesRepository;
import com.SoLAW.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Articles> getAllArticles() {
        return articlesRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    public List<Articles> getLatestArticles(int limit) {
        return articlesRepository.findTop3ByOrderByCreatedAtDesc();
    }

    public Articles getArticleById(Integer id) {
        return articlesRepository.findById(id).orElseThrow(() -> new RuntimeException("Article not found with id: " + id));
    }

    public String formatDate(LocalDateTime createdAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return createdAt.format(formatter);
    }

    public int getTotalArticles() {
        return articlesRepository.countAllArticles();
    }

    public int getPublishedArticles() {
        return articlesRepository.countPublishedArticles();
    }

    public int getDraftArticles() {
        return articlesRepository.countDraftArticles();
    }

    public Articles store(Articles articles, List<Integer> categoryId) {
        if (categoryId != null && !categoryId.isEmpty()) {
            List<Categories> categories = categoriesRepository.findAllById(categoryId);
            articles.setCategories(categories);
        }
        return articlesRepository.save(articles);
    }

    public Articles update(Integer id, Articles articlesDetails, List<Integer> categoryId) {
        Articles articles = getArticleById(id);

        articles.setTitle(articlesDetails.getTitle());
        articles.setDescription(articlesDetails.getDescription());
        articles.setContent(articlesDetails.getContent());
        articles.setThumbnailUrl(articlesDetails.getThumbnailUrl());
        articles.setStatus(articlesDetails.getStatus());

        if (categoryId != null && !categoryId.isEmpty()) {
            List<Categories> categories = categoriesRepository.findAllById(categoryId);
            articles.setCategories(categories);
        }

        return articlesRepository.save(articles);
    }

    public void deleteArticle(Integer id) {
        Articles articles = getArticleById(id);
        articlesRepository.delete(articles);
    }
}
