package com.luanvan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.BlogDTO;

public interface BlogService {
	boolean save(BlogDTO blog, MultipartFile thumbnail);
	void update(BlogDTO blog);
	List<BlogDTO> findAll();
	List<BlogDTO> findAll(int page, int limit);
	List<BlogDTO> findAll(int page, int limit, String name);
	List<BlogDTO> findAll(int page, int limit, Long topicId);
	List<BlogDTO> findAll(int page, int limit, String name, Long topicId);
	int totalItem();
	int totalItem(String name);
	int totalItem(Long topicId);
	int totalItem(String name, Long topicId);
	void delete(Long id);
	BlogDTO findById(Long id);
}
