package com.example.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.repository.*;
import com.example.util.*;
import com.example.dto.*;
import com.example.entities.*;
import com.example.support.*;

@Service
public class CategoryService {
	
	private CategoryRepository repoCat;

	@Autowired
	private CategoryEntityConverter converter;

	@Autowired
	public CategoryService(CategoryRepository repoCat) {
		this.repoCat = repoCat;
	}

	public List<CategoryDTO> findAll() {
		return repoCat.findAll().stream().map(CategoryEntityConverter::toDTO)
				.collect(Collectors.toList());
	}

	public CategoryEntity findById(Integer code) {
		Optional<CategoryEntity> obj = repoCat.findById(code);
		CategoryEntity entity = obj.orElseThrow(() -> new CategoryNotFoundException("Object not found: " + code));
		return entity;
	}

	public List<CategoryDTO> findByActive(boolean b) {
		return repoCat.findByActive(true).stream().map(CategoryEntityConverter::toDTO).collect(Collectors.toList());
	}

	public void createProduct(CategoryDTO product) {
		repoCat.save(converter.toEntity(product));
	}

	public void updateProduct(CategoryDTO cat, Integer code) {
		if (code == null || cat == null || !code.equals(cat.getId())) {
			throw new CategoryException("Invalid category name.");
		}
		CategoryEntity existingObj = findById(code);
		updateData(existingObj, cat);
		repoCat.save(existingObj);
	}

	private void updateData(CategoryEntity existingObj, CategoryDTO newObj) {
		existingObj.setName(newObj.getName());
	}

	public void deleteProduct(Integer code) {
		if (code == null) {
			throw new CategoryException("Product code can not be null.");
		}
		CategoryEntity obj = findById(code);
		try {
			repoCat.delete(obj);
			// desativar o produto (ao invés de deletar)
			//obj.setActive(false);
			//repo.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new CategoryException("Can not delete a Product with dependencies constraints.");
		}
	}

}
