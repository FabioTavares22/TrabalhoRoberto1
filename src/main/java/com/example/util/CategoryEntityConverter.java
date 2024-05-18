package com.example.util;

import org.springframework.stereotype.Component;
import com.example.dto.*;
import com.example.entities.*;

@Component
public class CategoryEntityConverter {
	
	public static CategoryDTO toDTO(CategoryEntity category) {
		return new CategoryDTO(
				0, category.getFamily(), category.getClasss(), 
				category.getName(),category.isActive());
	}
	
	public CategoryEntity toEntity(CategoryDTO cat) {
		System.out.println("toEntity: " + cat);
		return new CategoryEntity(0, cat.getName(), cat.getFamily(), cat.getClasss(), cat.isActive());
	}

}
