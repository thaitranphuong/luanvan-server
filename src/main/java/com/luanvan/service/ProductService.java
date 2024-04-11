package com.luanvan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.luanvan.dto.ProductDTO;

public interface ProductService {
	Long save(ProductDTO product, MultipartFile thumbnail);
	void update(ProductDTO product);
	List<ProductDTO> findAll();
	List<ProductDTO> findAll(int page, int limit);
	List<ProductDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	ProductDTO findById(Long id);
}
