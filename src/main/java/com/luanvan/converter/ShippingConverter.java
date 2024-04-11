package com.luanvan.converter;

import com.luanvan.dto.ShippingDTO;
import com.luanvan.entity.ShippingEntity;

public class ShippingConverter {
	public static ShippingEntity toEntity(ShippingDTO dto) {
		ShippingEntity entity = new ShippingEntity();
		entity.setName(dto.getName());
		entity.setCost(dto.getCost());
		entity.setRemoved(dto.isRemoved());
		return entity;
	}
	
	public static ShippingDTO toDTO(ShippingEntity entity) {
		ShippingDTO dto = new ShippingDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCost(entity.getCost());
		return dto;
	}
	

	public static ShippingEntity toEntity(ShippingDTO dto, ShippingEntity entity) {
		entity.setName(dto.getName());
		entity.setCost(dto.getCost());
		entity.setRemoved(dto.isRemoved());
		return entity;
	}
}
