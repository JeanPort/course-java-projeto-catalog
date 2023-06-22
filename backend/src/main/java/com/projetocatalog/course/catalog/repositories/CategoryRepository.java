package com.projetocatalog.course.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetocatalog.course.catalog.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
