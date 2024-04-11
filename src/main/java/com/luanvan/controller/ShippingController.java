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

import com.luanvan.dto.ShippingDTO;
import com.luanvan.dto.pagination.ShippingOutput;
import com.luanvan.service.ShippingService;

@RestController
@CrossOrigin
@RequestMapping("/shipping")
public class ShippingController {
	@Autowired
	private ShippingService shippingService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ShippingDTO save(@RequestBody ShippingDTO dto) {
	 shippingService.save(dto);
		return new ShippingDTO();
	}
	
	@GetMapping
	public ShippingOutput getShippings(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		ShippingOutput result = new ShippingOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(shippingService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) shippingService.totalItem(name) / limit));
		} else {
			result.setListResult(shippingService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) shippingService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ShippingDTO delete(@PathVariable Long id) {
	 shippingService.delete(id);
		return new ShippingDTO();
	}
	
	@GetMapping("/{id}")
	public ShippingDTO getShipping(@PathVariable Long id) {
		return shippingService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<ShippingDTO> getAllShippings() {
		return shippingService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ShippingDTO update(@RequestBody ShippingDTO dto) {
	 shippingService.update(dto);
		return new ShippingDTO();
	}
}
