package com.SoLAW.service;

import com.SoLAW.model.Articles;
import com.SoLAW.repository.ArticlesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticlesService {

    @Autowired
    private ArticlesRepository articlesRepository;

    public List<Articles> getAllArticles() {
        return articlesRepository.findAll();
    }

    public List<Articles> getLatestArticles(int limit) {
        return articlesRepository.findTop3ByOrderByCreatedAtDesc();
    }

}
