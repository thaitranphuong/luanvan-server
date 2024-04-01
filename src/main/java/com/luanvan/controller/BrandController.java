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

import com.luanvan.dto.BrandDTO;
import com.luanvan.dto.CategoryDTO;
import com.luanvan.dto.pagination.BrandOutput;
import com.luanvan.dto.pagination.CategoryOutput;
import com.luanvan.service.BrandService;
import com.luanvan.service.CategoryService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/brand")
public class BrandController {
	@Autowired
	private BrandService brandService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public BrandDTO save(@RequestBody BrandDTO brand) {
		brandService.save(brand);
		return new BrandDTO();
	}
	
	@GetMapping
	public BrandOutput getBrands(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		BrandOutput result = new BrandOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(brandService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) brandService.totalItem(name) / limit));
		} else {
			result.setListResult(brandService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) brandService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public BrandDTO delete(@PathVariable Long id) {
		brandService.delete(id);
		return new BrandDTO();
	}
	
	@GetMapping("/{id}")
	public BrandDTO getBrand(@PathVariable Long id) {
		return brandService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<BrandDTO> getAllBrands() {
		return brandService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public BrandDTO update(@RequestBody BrandDTO category) {
		brandService.update(category);
		return new BrandDTO();
	}
}
