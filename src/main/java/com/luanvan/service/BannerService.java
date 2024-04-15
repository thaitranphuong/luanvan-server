package com.luanvan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.BannerDTO;

public interface BannerService {
	boolean save(BannerDTO banner, MultipartFile image);
	void update(BannerDTO banner);
	List<BannerDTO> findAll();
	List<BannerDTO> findAll(int page, int limit);
	List<BannerDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	BannerDTO findById(Long id);
}
