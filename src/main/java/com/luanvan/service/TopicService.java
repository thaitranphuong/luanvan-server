package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.SupplierDTO;
import com.luanvan.dto.TopicDTO;

public interface TopicService {
	void save(TopicDTO supplier);
	void update(TopicDTO supplier);
	List<TopicDTO> findAll();
	List<TopicDTO> findAll(int page, int limit);
	List<TopicDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	TopicDTO findById(Long id);
}
