package com.luanvan.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.ProductDTO;
import com.luanvan.dto.ProductDetailDTO;

public interface ProductDetailService {
	Long save(ProductDetailDTO productDetail, MultipartFile image);
	//List<ProductDetailDTO> findAllByProductId(Long productId);
	void update(ProductDetailDTO productDetail);
	void deleteAllById(List<Long> listProductDetailId);
}
