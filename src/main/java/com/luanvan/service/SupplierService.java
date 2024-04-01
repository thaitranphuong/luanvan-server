package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.SupplierDTO;

public interface SupplierService {
	void save(SupplierDTO supplier);
	void update(SupplierDTO supplier);
	List<SupplierDTO> findAll();
	List<SupplierDTO> findAll(int page, int limit);
	List<SupplierDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	SupplierDTO findById(Long id);
}
