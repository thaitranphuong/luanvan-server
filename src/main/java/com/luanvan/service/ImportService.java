package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.ImportDTO;

public interface ImportService {
	Long save(ImportDTO supplier);
	List<ImportDTO> findAll();
	List<ImportDTO> findAll(int page, int limit);
	List<ImportDTO> findAll(int page, int limit, Long id);
	int totalItem();
	int totalItem(Long id);
	ImportDTO findById(Long id);
}
