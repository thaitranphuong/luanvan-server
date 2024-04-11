package com.luanvan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.converter.ProductDetailConverter;
import com.luanvan.dto.ProductDetailDTO;
import com.luanvan.dto.ProductDetailSizeDTO;
import com.luanvan.entity.ProductDetailEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.ProductDetailRepository;
import com.luanvan.service.ProductDetailService;
import com.luanvan.service.ProductDetailSizeService;

@Service
public class ProductDetailServiceImpl implements ProductDetailService{
	@Autowired
	private ProductDetailRepository productDetailRepository;
	
	@Autowired
	private ProductDetailSizeService productDetailSizeService;
	
	@Autowired
	private ProductDetailConverter productDetailConverter;

	@Override
	public Long save(ProductDetailDTO productDetail, MultipartFile image) {
		Path path = Paths.get("src/main/resources/static/product_details/");
		try {
			InputStream inputStream = image.getInputStream();
			Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			return productDetailRepository.save(productDetailConverter.toEntity(productDetail)).getId();
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void update(ProductDetailDTO dto) {
		ProductDetailEntity entity = productDetailRepository.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = productDetailConverter.toEntity(dto, entity);
			productDetailRepository.save(entity);
		}
	}

	@Override
	public void deleteAllById(List<Long> listProductDetailId) {
		productDetailRepository.deleteAllById(listProductDetailId);
	}

//	@Override
//	public List<ProductDetailDTO> findAllByProductId(Long productId) {
//		List<ProductDetailEntity> entities = productDetailRepository.findAllByProductId(productId);
//		List<ProductDetailDTO> DTOs = new ArrayList<>();
//		entities.forEach(item -> {
//			ProductDetailDTO dto = productDetailConverter.toDTO(item);
//			List<ProductDetailSizeDTO> productDetailSizeDTOs = productDetailSizeService.findAllByProductDetailId(item.getId());
//			dto.setListProductDetail(productDetailSizeDTOs);
//			DTOs.add(dto);
//		});
//		return DTOs;
//	}

}
