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

import com.luanvan.dto.WarehouseDTO;
import com.luanvan.dto.pagination.WarehouseOutput;
import com.luanvan.service.WarehouseService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/warehouse")
public class WarehouseController {
	@Autowired
	private WarehouseService warehouseService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public WarehouseDTO save(@RequestBody WarehouseDTO warehouse) {
		warehouseService.save(warehouse);
		return new WarehouseDTO();
	}
	
	@GetMapping
	public WarehouseOutput getWarehouses(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		WarehouseOutput result = new WarehouseOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(warehouseService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) warehouseService.totalItem(name) / limit));
		} else {
			result.setListResult(warehouseService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) warehouseService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public WarehouseDTO delete(@PathVariable Long id) {
		warehouseService.delete(id);
		return new WarehouseDTO();
	}
	
	@GetMapping("/{id}")
	public WarehouseDTO getWarehouse(@PathVariable Long id) {
		return warehouseService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<WarehouseDTO> getAllWarehouse() {
		return warehouseService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public WarehouseDTO update(@RequestBody WarehouseDTO warehouse) {
		warehouseService.update(warehouse);
		return new WarehouseDTO();
	}
}
