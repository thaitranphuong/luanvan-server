package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.ProductDetailSizeDTO;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.service.ProductDetailSizeService;

@RestController
@CrossOrigin
@RequestMapping("/product-detail-size")
public class ProductDetailSizeController {
	@Autowired
	private ProductDetailSizeService productDetailSizeService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody ProductDetailSizeDTO productDetailSize) {
		Long productDetailSizeId = productDetailSizeService.save(productDetailSize);
		if (productDetailSizeId != null)
			return ResponseEntity.ok(productDetailSizeId);
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public SuccessResponse deleteByProductDetailId(@PathVariable(name = "id") Long productDetailId) {
		productDetailSizeService.deleteByProductDetailId(productDetailId);
		return new SuccessResponse();
	}
}
