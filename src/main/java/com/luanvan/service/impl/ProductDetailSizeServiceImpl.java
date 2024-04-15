package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.ProductDetailSizeConverter;
import com.luanvan.dto.ProductDetailSizeDTO;
import com.luanvan.repository.ProductDetailSizeRepository;
import com.luanvan.service.ProductDetailSizeService;

@Service
public class ProductDetailSizeServiceImpl implements ProductDetailSizeService{
	@Autowired
	private ProductDetailSizeRepository productDetailSizeRepository;
	
	@Autowired
	private ProductDetailSizeConverter productDetailSizeConverter;
	@Override
	public Long save(ProductDetailSizeDTO productDetailSize) {
		return productDetailSizeRepository.save(productDetailSizeConverter.toEntity(productDetailSize)).getId();
	}
	@Override
	public void deleteByProductDetailId(Long productDetailId) {
		productDetailSizeRepository.deleteByProductDetailId(productDetailId);
	}
	
}
