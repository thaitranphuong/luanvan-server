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

import com.luanvan.dto.AddressDTO;
import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.service.AddressService;

@RestController
@CrossOrigin
@RequestMapping("/address")
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AddressDTO address) {
		if (addressService.save(address))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(addressService.delete(id))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping("/{id}")
	public AddressDTO getBanner(@PathVariable Long id) {
		return addressService.findById(id);
	}
	
	@GetMapping("/get-by-user/{userId}")
	public List<AddressDTO> getAllBanners(@PathVariable Long userId) {
		return addressService.findAll(userId);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody AddressDTO dto) {
		if(addressService.update(dto))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
