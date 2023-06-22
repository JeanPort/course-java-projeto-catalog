package com.projetocatalog.course.catalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetocatalog.course.catalog.dto.CategoryDTO;
import com.projetocatalog.course.catalog.entities.Category;
import com.projetocatalog.course.catalog.repositories.CategoryRepository;
import com.projetocatalog.course.catalog.services.exceptions.EntityNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public CategoryDTO insert(CategoryDTO obj) {
		Category entity = update(obj);
		entity = repository.save(entity);
		return new CategoryDTO(entity);
		
	}
	
	private Category update(CategoryDTO obj) {
		Category entity = new Category(null, obj.getName());
		return entity;
	}
}
