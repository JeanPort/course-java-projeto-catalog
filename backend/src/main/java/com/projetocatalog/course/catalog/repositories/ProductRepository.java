package com.projetocatalog.course.catalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetocatalog.course.catalog.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
