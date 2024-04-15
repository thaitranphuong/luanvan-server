package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.ImportDetailDTO;
import com.luanvan.entity.ImportDetailEntity;
import com.luanvan.entity.ImportEntity;
import com.luanvan.entity.ProductDetailEntity;
import com.luanvan.entity.ProductDetailSizeEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.ImportRepository;
import com.luanvan.repository.ProductDetailSizeRepository;

@Component
public class ImportDetailConverter {
	@Autowired
	private ImportRepository importRepository;
	
	@Autowired
	private ProductDetailSizeRepository productDetailSizeRepository;
	
	public ImportDetailEntity toEntity(ImportDetailDTO dto) {
		ImportDetailEntity entity = new ImportDetailEntity();
		entity.setPrice(dto.getPrice());
		entity.setQuantity(dto.getQuantity());
		ImportEntity importEntity = importRepository.findById(dto.getImportId()).get();
		entity.set_import(importEntity);
		ProductDetailSizeEntity productDetailSizeEntity = productDetailSizeRepository.findById(dto.getProductDetailSizeId()).get();
		entity.setProductDetailSize(productDetailSizeEntity);
		return entity;
	}
	
	public ImportDetailDTO toDTO(ImportDetailEntity entity) {
		ImportDetailDTO dto = new ImportDetailDTO();
		dto.setId(entity.getId());
		dto.setPrice(entity.getPrice());
		dto.setQuantity(entity.getQuantity());
		ProductDetailSizeEntity productDetailSizeEntity = productDetailSizeRepository.findById(entity.getProductDetailSize().getId()).get();
		ProductDetailEntity productDetailEntity = productDetailSizeEntity.getProductDetail();
		ProductEntity productEntity = productDetailEntity.getProduct();
		dto.setProductName(productEntity.getName() + " - " + productDetailEntity.getColor() + " - " + productDetailSizeEntity.getSize());
		return dto;
	}
}
