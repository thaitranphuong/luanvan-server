package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.WarehouseConverter;
import com.luanvan.dto.WarehouseDTO;
import com.luanvan.entity.WarehouseEntity;
import com.luanvan.repository.WarehouseRepository;
import com.luanvan.service.WarehouseService;

@Service
public class WarehouseServiceImpl implements WarehouseService{
	@Autowired
	private WarehouseRepository warehouseRepo;

	@Override
	public void save(WarehouseDTO warehouse) {
		warehouseRepo.save(WarehouseConverter.toEntity(warehouse));
	}

	@Override
	public List<WarehouseDTO> findAll(int page, int limit) {
		List<WarehouseEntity> warehouseEntities = warehouseRepo.findAll(page, limit);
		List<WarehouseDTO> warehouses = new ArrayList<>();
		for(WarehouseEntity item: warehouseEntities) {
			WarehouseDTO warehouseDTO = WarehouseConverter.toDTO(item);
			warehouses.add(warehouseDTO);
		}
		return warehouses;
	}

	@Override
	public List<WarehouseDTO> findAll(int page, int limit, String name) {
		List<WarehouseEntity> warehouseEntities = warehouseRepo.findAll(name, page, limit);
		List<WarehouseDTO> warehouses = new ArrayList<>();
		for(WarehouseEntity item: warehouseEntities) {
			WarehouseDTO warehouseDTO = WarehouseConverter.toDTO(item);
			warehouses.add(warehouseDTO);
		}
		return warehouses;
	}

	@Override
	public int totalItem() {
		List<WarehouseEntity> result = warehouseRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<WarehouseEntity> result = warehouseRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		warehouseRepo.deleteById(id);
	}

	@Override
	public WarehouseDTO findById(Long id) {
		WarehouseEntity warehouseEntity = warehouseRepo.findById(id).orElse(null);
		WarehouseDTO warehouseDTO;
		if(warehouseEntity != null) {
			warehouseDTO = WarehouseConverter.toDTO(warehouseEntity);
		} else {
			warehouseDTO = new WarehouseDTO();
		}
		return warehouseDTO;
	}

	@Override
	public List<WarehouseDTO> findAll() {
		List<WarehouseEntity> warehouseEntities = warehouseRepo.findAll();
		List<WarehouseDTO> warehouseDTOs = new ArrayList<>();
		for (WarehouseEntity item : warehouseEntities) {
			WarehouseDTO dto = WarehouseConverter.toDTO(item);
			warehouseDTOs.add(dto);
		}
		return warehouseDTOs;
	}

	@Override
	public void update(WarehouseDTO category) {
		WarehouseEntity warehouseEntity = warehouseRepo.findById(category.getId()).orElse(null);
		if(warehouseEntity != null) {
			warehouseEntity = WarehouseConverter.toEntity(category, warehouseEntity);
			warehouseRepo.save(warehouseEntity);
		}
	}
}
