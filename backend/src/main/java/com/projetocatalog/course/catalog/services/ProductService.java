package com.projetocatalog.course.catalog.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetocatalog.course.catalog.dto.ProductDTO;
import com.projetocatalog.course.catalog.entities.Product;
import com.projetocatalog.course.catalog.repositories.ProductRepository;
import com.projetocatalog.course.catalog.services.exceptions.DatabaseException;
import com.projetocatalog.course.catalog.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	/*
	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll();
		
		return list.stream().map(x -> new ProductDTO(x)).collect(Collectors.toList());
		 Forma 2 de fazer!!
		List<ProductDTO> listDtos = new ArrayList<>();
		
		for (Product category : list) {
			listDtos.add(new ProductDTO(category));
		}
		return listDtos;
	}
	*/
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new ProductDTO(entity, entity.getCategories());
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO obj) {
		Product entity = new Product(/*null, obj.getName()*/);
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
	}
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			//entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
		}
		catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

	public void delete(Long id) {
		try {
		repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrit violation");
		}
	}

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> list = repository.findAll(pageRequest);
		return list.map(x -> new ProductDTO(x));
	}

	
}
