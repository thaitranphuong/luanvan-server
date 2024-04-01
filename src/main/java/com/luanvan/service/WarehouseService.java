package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.WarehouseDTO;

public interface WarehouseService {
	void save(WarehouseDTO warehouse);
	void update(WarehouseDTO warehouse);
	List<WarehouseDTO> findAll();
	List<WarehouseDTO> findAll(int page, int limit);
	List<WarehouseDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	WarehouseDTO findById(Long id);
}
