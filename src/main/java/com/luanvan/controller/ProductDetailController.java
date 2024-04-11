package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.ProductDTO;
import com.luanvan.dto.ProductDetailDTO;
import com.luanvan.service.ProductDetailService;

@RestController
@CrossOrigin
@RequestMapping("/product-detail")
public class ProductDetailController {
	@Autowired
	private ProductDetailService productDetailService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestPart("productDetail") ProductDetailDTO productDetail, @RequestPart("image") MultipartFile image) {
		Long productDetailId = productDetailService.save(productDetail, image);
		if (productDetailId != null)
			return ResponseEntity.ok(productDetailId);
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ProductDTO update(@RequestBody ProductDetailDTO productDetail) {
		productDetailService.update(productDetail);
		return new ProductDTO();
	}
	
	@PostMapping("/delete-batch")
	@PreAuthorize("hasRole('ADMIN')")
	public ProductDTO deleteAllById(@RequestBody List<Long> listProductDetailId) {
		productDetailService.deleteAllById(listProductDetailId);
		return new ProductDTO();
	}
}
