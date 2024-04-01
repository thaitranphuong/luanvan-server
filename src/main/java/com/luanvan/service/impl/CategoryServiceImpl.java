package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.CategoryConverter;
import com.luanvan.dto.CategoryDTO;
import com.luanvan.entity.CategoryEntity;
import com.luanvan.repository.CategoryRepository;
import com.luanvan.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public void save(CategoryDTO category) {
		categoryRepo.save(CategoryConverter.toEntity(category));
	}

	@Override
	public List<CategoryDTO> findAll(int page, int limit) {
		List<CategoryEntity> categoryEntities = categoryRepo.findAll(page, limit);
		List<CategoryDTO> categories = new ArrayList<>();
		for(CategoryEntity item: categoryEntities) {
			CategoryDTO categoryDTO = CategoryConverter.toDTO(item);
			categories.add(categoryDTO);
		}
		return categories;
	}

	@Override
	public List<CategoryDTO> findAll(int page, int limit, String name) {
		List<CategoryEntity> categoryEntities = categoryRepo.findAll(name, page, limit);
		List<CategoryDTO> categories = new ArrayList<>();
		for(CategoryEntity item: categoryEntities) {
			CategoryDTO categoryDTO = CategoryConverter.toDTO(item);
			categories.add(categoryDTO);
		}
		return categories;
	}

	@Override
	public int totalItem() {
		List<CategoryEntity> result = categoryRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<CategoryEntity> result = categoryRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		categoryRepo.deleteById(id);
	}

	@Override
	public CategoryDTO findById(Long id) {
		CategoryEntity categoryEntity = categoryRepo.findById(id).orElse(null);
		CategoryDTO categoryDTO;
		if(categoryEntity != null) {
			categoryDTO = CategoryConverter.toDTO(categoryEntity);
		} else {
			categoryDTO = new CategoryDTO();
		}
		return categoryDTO;
	}

	@Override
	public List<CategoryDTO> findAll() {
		List<CategoryEntity> categoryEntities = categoryRepo.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		for (CategoryEntity item : categoryEntities) {
			CategoryDTO dto = CategoryConverter.toDTO(item);
			categoryDTOs.add(dto);
		}
		return categoryDTOs;
	}

	@Override
	public void update(CategoryDTO category) {
		CategoryEntity categoryEntity = categoryRepo.findById(category.getId()).orElse(null);
		if(categoryEntity != null) {
			categoryEntity = CategoryConverter.toEntity(category, categoryEntity);
			categoryRepo.save(categoryEntity);
		}
	}

}
