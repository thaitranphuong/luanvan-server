package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.ProductDetailSizeDTO;

public interface ProductDetailSizeService {
	Long save(ProductDetailSizeDTO productDetailSize);
	//List<ProductDetailSizeDTO> findAllByProductDetailId(Long productDetailId);
	void deleteByProductDetailId(Long productDetailId);
}
