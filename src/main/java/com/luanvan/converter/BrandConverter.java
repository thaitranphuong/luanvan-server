package com.luanvan.converter;

import com.luanvan.dto.BrandDTO;
import com.luanvan.entity.BrandEntity;

public class BrandConverter {
	public static BrandEntity toEntity(BrandDTO brandDTO) {
		BrandEntity brandEntity = new BrandEntity();
		brandEntity.setId(brandDTO.getId());
		brandEntity.setName(brandDTO.getName());
		brandEntity.setCode(brandDTO.getCode());
		return brandEntity;
	}
	
	public static BrandDTO toDTO(BrandEntity brandEntity) {
		BrandDTO brandDTO = new BrandDTO();
		brandDTO.setId(brandEntity.getId());
		brandDTO.setName(brandEntity.getName());
		brandDTO.setCode(brandEntity.getCode());
		return brandDTO;
	}
	

	public static BrandEntity toEntity(BrandDTO brandDTO, BrandEntity brandEntity) {
		brandEntity.setName(brandDTO.getName());
		brandEntity.setCode(brandDTO.getCode());
		return brandEntity;
	}
}
