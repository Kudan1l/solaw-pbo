package com.SoLAW.repository;

import com.SoLAW.model.Articles;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Integer> {

    List<Articles> findAll(Sort sort);

    List<Articles> findTop3ByOrderByCreatedAtDesc();

    @Query("SELECT COUNT(a) FROM Articles a")
    int countAllArticles();

    @Query("SELECT COUNT(a) FROM Articles a WHERE a.status = 'Publish'")
    int countPublishedArticles();

    @Query("SELECT COUNT(a) FROM Articles a WHERE a.status = 'Draft'")
    int countDraftArticles();
}
