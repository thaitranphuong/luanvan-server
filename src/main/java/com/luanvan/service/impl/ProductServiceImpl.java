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

import com.luanvan.converter.ProductConverter;
import com.luanvan.converter.ProductDetailConverter;
import com.luanvan.converter.ProductDetailSizeConverter;
import com.luanvan.converter.SupplierConverter;
import com.luanvan.dto.ProductDTO;
import com.luanvan.dto.ProductDetailDTO;
import com.luanvan.dto.ProductDetailSizeDTO;
import com.luanvan.dto.SupplierDTO;
import com.luanvan.entity.ProductEntity;
import com.luanvan.entity.SupplierEntity;
import com.luanvan.repository.ProductRepository;
import com.luanvan.service.ProductDetailService;
import com.luanvan.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDetailConverter productDetailConverter;
	
	@Autowired
	private ProductDetailSizeConverter productDetailSizeConverter;
	
	@Autowired
	private ProductConverter productConverter;

	@Override
	public Long save(ProductDTO product, MultipartFile thumbnail) {
		Path path = Paths.get("src/main/resources/static/products/");
		try {
			InputStream inputStream = thumbnail.getInputStream();
			Files.copy(inputStream, path.resolve(thumbnail.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			return productRepository.save(productConverter.toEntity(product)).getId();
		} catch (IOException e) {
			return null;
		}
	}
	
	@Override
	public List<ProductDTO> findAll(int page, int limit) {
		List<ProductEntity> entities = productRepository.findAll(page, limit);
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<ProductDTO> findAll(int page, int limit, String name) {
		List<ProductEntity> entities = productRepository.findAll(name, page, limit);
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<ProductEntity> result = productRepository.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<ProductEntity> result = productRepository.findAll(name);
		return result.size();
	}
	
	@Override
	public ProductDTO findById(Long id) {
		ProductEntity productEntity = productRepository.findById(id).orElse(null);
		ProductDTO dto;
		if(productEntity != null) {
			dto = productConverter.toDTO(productEntity);
			List<ProductDetailDTO> productDetailDTOs = new ArrayList<>();
			productEntity.getProductDetails().forEach(productDetailEntity -> {
				ProductDetailDTO productDetailDTO = productDetailConverter.toDTO(productDetailEntity);
				List<ProductDetailSizeDTO> productDetailSizeDTOs = new ArrayList<>();
				productDetailEntity.getProductDetailSizes().forEach(productDetailSizeEntity -> {
					ProductDetailSizeDTO productDetailSizeDTO = productDetailSizeConverter.toDTO(productDetailSizeEntity);
					productDetailSizeDTOs.add(productDetailSizeDTO);
				});
				productDetailDTO.setListProductDetailSizes(productDetailSizeDTOs);
				productDetailDTOs.add(productDetailDTO);
			});
			dto.setListProductDetail(productDetailDTOs);
		} else {
			dto = new ProductDTO();
		}
		return dto;
	}

	@Override
	public List<ProductDTO> findAll() {
		List<ProductEntity> entities = productRepository.findAll();
		List<ProductDTO> DTOs = new ArrayList<>();
		for (ProductEntity item : entities) {
			ProductDTO dto = productConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(ProductDTO dto) {
		ProductEntity entity = productRepository.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = productConverter.toEntity(dto, entity);
			productRepository.save(entity);
		}
	}
	

}
