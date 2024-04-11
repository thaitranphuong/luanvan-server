package com.luanvan.converter;

import com.luanvan.dto.SupplierDTO;
import com.luanvan.entity.SupplierEntity;

public class SupplierConverter {
	public static SupplierEntity toEntity(SupplierDTO dto) {
		SupplierEntity entity = new SupplierEntity();
		entity.setName(dto.getName());
		entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
		return entity;
	}
	
	public static SupplierDTO toDTO(SupplierEntity entity) {
		SupplierDTO dto = new SupplierDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAddress(entity.getAddress());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
		return dto;
	}
	

	public static SupplierEntity toEntity(SupplierDTO dto, SupplierEntity entity) {
		entity.setName(dto.getName());
		entity.setAddress(dto.getAddress());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
		return entity;
	}
}
