package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.CartItemDTO;
import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.service.CartItemService;

@RestController
@CrossOrigin
@RequestMapping("/cart")
public class CartItemController {
	@Autowired
	private CartItemService cartItemService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CartItemDTO dto) {
		if (cartItemService.save(dto)) return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if (cartItemService.delete(id)) return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<?> getCart(@PathVariable Long customerId) {
		List<CartItemDTO> result = cartItemService.findByCustomerId(customerId);
		if (result != null) return ResponseEntity.ok(result);
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody CartItemDTO dto) {
		if (cartItemService.update(dto)) return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
