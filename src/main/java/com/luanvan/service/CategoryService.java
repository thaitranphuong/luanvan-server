package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.CategoryDTO;
import com.luanvan.entity.CategoryEntity;

public interface CategoryService {
	void save(CategoryDTO category);
	void update(CategoryDTO category);
	List<CategoryDTO> findAll();
	List<CategoryDTO> findAll(int page, int limit);
	List<CategoryDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	CategoryDTO findById(Long id);
}
