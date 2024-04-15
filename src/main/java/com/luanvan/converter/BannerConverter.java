package com.luanvan.converter;

import com.luanvan.dto.BannerDTO;
import com.luanvan.entity.BannerEntity;

public class BannerConverter {
	public static BannerEntity toEntity(BannerDTO dto) {
		BannerEntity entity = new BannerEntity();
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		return entity;
	}
	
	public static BannerDTO toDTO(BannerEntity entity) {
		BannerDTO dto = new BannerDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setImage(entity.getImage());
		return dto;
	}
	

	public static BannerEntity toEntity(BannerDTO dto, BannerEntity entity) {
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		return entity;
	}
}
