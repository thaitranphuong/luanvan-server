package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.ProductDetailDTO;
import com.luanvan.entity.ProductDetailEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.ProductRepository;

@Component
public class ProductDetailConverter {
	@Autowired
	private ProductRepository productRepository;
	
	public ProductDetailEntity toEntity(ProductDetailDTO dto) {
		ProductDetailEntity entity = new ProductDetailEntity();
		entity.setImage(dto.getImage());
		entity.setColor(dto.getColor());
		entity.setPrice(dto.getPrice());
		ProductEntity productEntity = productRepository.findById(dto.getProductId()).orElse(null);
		entity.setProduct(productEntity);
		return entity;
	}
	
	public static ProductDetailDTO toDTO(ProductDetailEntity entity) {
		ProductDetailDTO dto = new ProductDetailDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setColor(entity.getColor());
		dto.setPrice(entity.getPrice());
		dto.setProductId(entity.getProduct().getId());
		return dto;
	}
	

	public ProductDetailEntity toEntity(ProductDetailDTO dto, ProductDetailEntity entity) {
		entity.setImage(dto.getImage());
		entity.setColor(dto.getColor());
		entity.setPrice(dto.getPrice());
		ProductEntity productEntity = productRepository.findById(dto.getProductId()).orElse(null);
		entity.setProduct(productEntity);
		return entity;
	}
}
