package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.ProductDTO;
import com.luanvan.entity.BrandEntity;
import com.luanvan.entity.CategoryEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.BrandRepository;
import com.luanvan.repository.CategoryRepository;

@Component
public class ProductConverter {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private BrandRepository brandRepository;
	
	public ProductEntity toEntity(ProductDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setName(dto.getName());
		entity.setShortDescription(dto.getShortDescription());
		entity.setFullDescription(dto.getFullDescription());
		entity.setMaterial(dto.getMaterial());
		entity.setOrigin(dto.getOrigin());
		entity.setThumbnail(dto.getThumbnail());
		entity.setSoldQuantity(dto.getSoldQuantity());
		entity.setPercentDiscount(dto.getPercentDiscount());
		entity.setEnabled(dto.isEnabled());
		CategoryEntity categoryEntity = categoryRepository.findById(dto.getCategoryId()).orElse(null);
		BrandEntity brandEntity = brandRepository.findById(dto.getBrandId()).orElse(null);
		entity.setCategory(categoryEntity);
		entity.setBrand(brandEntity);
		return entity;
	}
	
	public static ProductDTO toDTO(ProductEntity entity) {
		ProductDTO dto = new ProductDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setShortDescription(entity.getShortDescription());
		dto.setFullDescription(entity.getFullDescription());
		dto.setMaterial(entity.getMaterial());
		dto.setOrigin(entity.getOrigin());
		dto.setThumbnail(entity.getThumbnail());
		dto.setSoldQuantity(entity.getSoldQuantity());
		dto.setPercentDiscount(entity.getPercentDiscount());
		dto.setEnabled(entity.isEnabled());
		dto.setCategoryId(entity.getCategory().getId());
		dto.setCategoryName(entity.getCategory().getName());
		dto.setBrandId(entity.getBrand().getId());
		dto.setBrandName(entity.getBrand().getName());
		return dto;
	}
	

	public ProductEntity toEntity(ProductDTO dto, ProductEntity entity) {
		entity.setName(dto.getName());
		entity.setShortDescription(dto.getShortDescription());
		entity.setFullDescription(dto.getFullDescription());
		entity.setMaterial(dto.getMaterial());
		entity.setOrigin(dto.getOrigin());
		entity.setThumbnail(dto.getThumbnail());
		entity.setSoldQuantity(dto.getSoldQuantity());
		entity.setPercentDiscount(dto.getPercentDiscount());
		entity.setEnabled(dto.isEnabled());
		CategoryEntity categoryEntity = categoryRepository.findById(dto.getCategoryId()).orElse(null);
		BrandEntity brandEntity = brandRepository.findById(dto.getBrandId()).orElse(null);
		entity.setCategory(categoryEntity);
		entity.setBrand(brandEntity);
		return entity;
	}
}
