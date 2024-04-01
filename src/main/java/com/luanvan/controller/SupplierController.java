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

import com.luanvan.dto.SupplierDTO;
import com.luanvan.dto.pagination.SupplierOutput;
import com.luanvan.service.SupplierService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public SupplierDTO save(@RequestBody SupplierDTO dto) {
		supplierService.save(dto);
		return new SupplierDTO();
	}
	
	@GetMapping
	public SupplierOutput getSuppliers(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		SupplierOutput result = new SupplierOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(supplierService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) supplierService.totalItem(name) / limit));
		} else {
			result.setListResult(supplierService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) supplierService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public SupplierDTO delete(@PathVariable Long id) {
		supplierService.delete(id);
		return new SupplierDTO();
	}
	
	@GetMapping("/{id}")
	public SupplierDTO getSupplier(@PathVariable Long id) {
		return supplierService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<SupplierDTO> getAllCategories() {
		return supplierService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public SupplierDTO update(@RequestBody SupplierDTO dto) {
		supplierService.update(dto);
		return new SupplierDTO();
	}
}
