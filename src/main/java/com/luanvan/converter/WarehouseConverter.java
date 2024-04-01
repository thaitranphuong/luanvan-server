package com.luanvan.converter;

import com.luanvan.dto.WarehouseDTO;
import com.luanvan.entity.WarehouseEntity;

public class WarehouseConverter {
	public static WarehouseEntity toEntity(WarehouseDTO warehouseDTO) {
		WarehouseEntity warehouseEntity = new WarehouseEntity();
		warehouseEntity.setId(warehouseDTO.getId());
		warehouseEntity.setName(warehouseDTO.getName());
		warehouseEntity.setAddress(warehouseDTO.getAddress());
		return warehouseEntity;
	}
	
	public static WarehouseDTO toDTO(WarehouseEntity warehouseEntity) {
		WarehouseDTO warehouseDTO = new WarehouseDTO();
		warehouseDTO.setId(warehouseEntity.getId());
		warehouseDTO.setName(warehouseEntity.getName());
		warehouseDTO.setAddress(warehouseEntity.getAddress());
		return warehouseDTO;
	}
	

	public static WarehouseEntity toEntity(WarehouseDTO warehouseDTO, WarehouseEntity warehouseEntity) {
		warehouseEntity.setName(warehouseDTO.getName());
		warehouseEntity.setAddress(warehouseDTO.getAddress());
		return warehouseEntity;
	}
}
