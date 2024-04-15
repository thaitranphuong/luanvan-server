package com.luanvan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.luanvan.dto.ProductDTO;
import com.luanvan.dto.ProductRateDTO;

public interface ProductService {
	Long save(ProductDTO product, MultipartFile thumbnail);
	void update(ProductDTO product);
	List<ProductDTO> findAll();
	List<ProductDTO> findAllCustomerPage(int page, int limit, String strCategoryId, String strBrandId);
	List<ProductDTO> findAllCustomerPage(int page, int limit, String name, String strCategoryId, String strBrandId);
	int totalItemCustomerPage(String strCategoryId, String strBrandId);
	int totalItemCustomerPage(String name, String strCategoryId, String strBrandId);
	List<ProductDTO> findAll(int page, int limit);
	List<ProductDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	ProductDTO findById(Long id);
	List<ProductDTO> findBestSales();
	List<ProductDTO> findNewProducts();
	ProductRateDTO findRate(Long id);
}
