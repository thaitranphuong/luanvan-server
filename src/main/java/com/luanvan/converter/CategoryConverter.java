package com.luanvan.converter;

import com.luanvan.dto.CategoryDTO;
import com.luanvan.entity.CategoryEntity;

public class CategoryConverter {
	
	public static CategoryEntity toEntity(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = new CategoryEntity();
		categoryEntity.setId(categoryDTO.getId());
		categoryEntity.setName(categoryDTO.getName());
		categoryEntity.setCode(categoryDTO.getCode());
		return categoryEntity;
	}
	
	public static CategoryDTO toDTO(CategoryEntity categoryEntity) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setId(categoryEntity.getId());
		categoryDTO.setName(categoryEntity.getName());
		categoryDTO.setCode(categoryEntity.getCode());
		return categoryDTO;
	}
	

	public static CategoryEntity toEntity(CategoryDTO categoryDTO, CategoryEntity categoryEntity) {
		categoryEntity.setName(categoryDTO.getName());
		categoryEntity.setCode(categoryDTO.getCode());
		return categoryEntity;
	}
}
