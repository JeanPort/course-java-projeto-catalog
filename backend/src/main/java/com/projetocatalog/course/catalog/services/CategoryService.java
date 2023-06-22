package com.projetocatalog.course.catalog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetocatalog.course.catalog.dto.CategoryDTO;
import com.projetocatalog.course.catalog.entities.Category;
import com.projetocatalog.course.catalog.repositories.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		/*
		 * 
		 * Forma 2 de fazer!!
		List<CategoryDTO> listDtos = new ArrayList<>();
		
		for (Category category : list) {
			listDtos.add(new CategoryDTO(category));
		}
		return listDtos;
		*/
	
	}
}
