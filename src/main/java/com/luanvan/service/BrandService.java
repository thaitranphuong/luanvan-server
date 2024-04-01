package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.BrandDTO;

public interface BrandService {
	void save(BrandDTO brand);
	void update(BrandDTO brand);
	List<BrandDTO> findAll();
	List<BrandDTO> findAll(int page, int limit);
	List<BrandDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	BrandDTO findById(Long id);
}
