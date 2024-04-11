package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.ShippingDTO;

public interface ShippingService {
	void save(ShippingDTO supplier);
	void update(ShippingDTO supplier);
	List<ShippingDTO> findAll();
	List<ShippingDTO> findAll(int page, int limit);
	List<ShippingDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	ShippingDTO findById(Long id);
}
