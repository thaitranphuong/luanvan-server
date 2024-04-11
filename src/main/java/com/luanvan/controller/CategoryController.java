package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.CategoryDTO;
import com.luanvan.dto.pagination.CategoryOutput;
import com.luanvan.entity.CategoryEntity;
import com.luanvan.service.CategoryService;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public CategoryDTO save(@RequestBody CategoryDTO category) {
		categoryService.save(category);
		return new CategoryDTO();
	}
	
	@GetMapping
	public CategoryOutput getCategories(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		CategoryOutput result = new CategoryOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(categoryService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) categoryService.totalItem(name) / limit));
		} else {
			result.setListResult(categoryService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) categoryService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public CategoryDTO delete(@PathVariable Long id) {
		categoryService.delete(id);
		return new CategoryDTO();
	}
	
	@GetMapping("/{id}")
	public CategoryDTO getCategory(@PathVariable Long id) {
		return categoryService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<CategoryDTO> getAllCategories() {
		return categoryService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public CategoryDTO update(@RequestBody CategoryDTO category) {
		categoryService.update(category);
		return new CategoryDTO();
	}
}
