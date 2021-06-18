package com.project.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.inventorymanagement.domain.Article;

public interface ArticleRespository extends JpaRepository<Article, Long> {

}
