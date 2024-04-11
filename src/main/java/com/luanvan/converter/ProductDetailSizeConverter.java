package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.ProductDetailSizeDTO;
import com.luanvan.entity.ProductDetailEntity;
import com.luanvan.entity.ProductDetailSizeEntity;
import com.luanvan.repository.ProductDetailRepository;

@Component
public class ProductDetailSizeConverter {
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	public ProductDetailSizeEntity toEntity(ProductDetailSizeDTO dto) {
		ProductDetailSizeEntity entity = new ProductDetailSizeEntity();
		entity.setSize(dto.getSize());
		entity.setQuantity(dto.getQuantity());
		ProductDetailEntity productDetailEntity = productDetailRepository.findById(dto.getProductDetailId()).orElse(null);
		entity.setProductDetail(productDetailEntity);
		return entity;
	}
	
	public static ProductDetailSizeDTO toDTO(ProductDetailSizeEntity entity) {
		ProductDetailSizeDTO dto = new ProductDetailSizeDTO();
		dto.setId(entity.getId());
		dto.setSize(entity.getSize());
		dto.setQuantity(entity.getQuantity());
		dto.setProductDetailId(entity.getProductDetail().getId());
		return dto;
	}
	

	public ProductDetailSizeEntity toEntity(ProductDetailSizeDTO dto, ProductDetailSizeEntity entity) {
		entity.setSize(dto.getSize());
		entity.setQuantity(dto.getQuantity());
		ProductDetailEntity productDetailEntity = productDetailRepository.findById(dto.getProductDetailId()).orElse(null);
		entity.setProductDetail(productDetailEntity);
		return entity;
	}
}
