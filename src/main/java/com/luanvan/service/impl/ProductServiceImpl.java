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
import com.luanvan.dto.ProductRateDTO;
import com.luanvan.dto.SupplierDTO;
import com.luanvan.entity.CommentEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.entity.SupplierEntity;
import com.luanvan.repository.CommentRepository;
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
	
	@Autowired
	private CommentRepository commentRepository;

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
	public List<ProductDTO> findAllCustomerPage(int page, int limit, String strCategoryId, String strBrandId) {
		List<ProductEntity> entities = new ArrayList<>();
		if(!strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByCategoryAndBrand(page, limit, strCategoryId, strBrandId);
		} else if(strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByBrand(page, limit, strBrandId);
		} else if(!strCategoryId.equals("none") && strBrandId.equals("none")) {
			entities = productRepository.findAllByCategory(page, limit, strCategoryId);
		} else {
			entities = productRepository.findAllCustomerPage(page, limit);
		}
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			if (item.getProductDetails().size() > 0)
				dto.setShowedPrice(item.getProductDetails().get(0).getPrice());
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<ProductDTO> findAllCustomerPage(int page, int limit, String name, String strCategoryId, String strBrandId) {
		List<ProductEntity> entities = new ArrayList<>();
		if(!strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByCategoryAndBrand(name, page, limit, strCategoryId, strBrandId);
		} else if(strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByBrand(name, page, limit, strBrandId);
		} else if(!strCategoryId.equals("none") && strBrandId.equals("none")) {
			entities = productRepository.findAllByCategory(name, page, limit, strCategoryId);
		} else {
			entities = productRepository.findAllCustomerPage(name, page, limit);
		}
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			if (item.getProductDetails().size() > 0)
				dto.setShowedPrice(item.getProductDetails().get(0).getPrice());
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItemCustomerPage(String strCategoryId, String strBrandId) {
		List<ProductEntity> entities = new ArrayList<>();
		if(!strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByCategoryAndBrand(strCategoryId, strBrandId);
		} else if(strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByBrand(strBrandId);
		} else if(!strCategoryId.equals("none") && strBrandId.equals("none")) {
			entities = productRepository.findAllByCategory(strCategoryId);
		} else {
			entities = productRepository.findAllCustomerPage();
		}
		return entities.size();
	}

	@Override
	public int totalItemCustomerPage(String name, String strCategoryId, String strBrandId) {
		List<ProductEntity> entities = new ArrayList<>();
		if(!strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByCategoryAndBrand(name, strCategoryId, strBrandId);
		} else if(strCategoryId.equals("none") && !strBrandId.equals("none")) {
			entities = productRepository.findAllByBrand(name, strBrandId);
		} else if(!strCategoryId.equals("none") && strBrandId.equals("none")) {
			entities = productRepository.findAllByCategory(name, strCategoryId);
		} else {
			entities = productRepository.findAllCustomerPage(name);
		}
		return entities.size();
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
		List<ProductEntity> entities = productRepository.findAll();
		return entities.size();
	}

	@Override
	public int totalItem(String name) {
		List<ProductEntity> entities = productRepository.findAll(name);
		return entities.size();
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
			if (productEntity.getProductDetails().size() > 0)
				dto.setShowedPrice(productEntity.getProductDetails().get(0).getPrice());
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

	@Override
	public List<ProductDTO> findBestSales() {
		List<ProductEntity> entities  =  productRepository.findBestSales();
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			if (item.getProductDetails().size() > 0)
				dto.setShowedPrice(item.getProductDetails().get(0).getPrice());
			DTOs.add(dto);
		}
		return DTOs;
	}
	
	@Override
	public List<ProductDTO> findNewProducts() {
		List<ProductEntity> entities  =  productRepository.findNewProducts();
		List<ProductDTO> DTOs = new ArrayList<>();
		for(ProductEntity item: entities) {
			ProductDTO dto = productConverter.toDTO(item);
			if (item.getProductDetails().size() > 0)
				dto.setShowedPrice(item.getProductDetails().get(0).getPrice());
			DTOs.add(dto);
		}
		return DTOs;
	}

	int oneStarQuantity = 0;
	int twoStarQuantity = 0;
	int threeStarQuantity = 0;
	int fourStarQuantity = 0;
	int fiveStarQuantity = 0;
	float averageStar = 0f;
	int commentQuantity = 0;

	@Override
	public ProductRateDTO findRate(Long id) {
		List<CommentEntity> comments = commentRepository.findAllByProductId(String.valueOf(id));
		comments.forEach(item -> {
			int star = item.getStar();
			if (star == 1) oneStarQuantity++;
			else if (star == 2) twoStarQuantity++;
			else if (star == 3) threeStarQuantity++;
			else if (star == 4) fourStarQuantity++;
			else fiveStarQuantity++;
		});
		commentQuantity = comments.size();
		averageStar = (float)(1*oneStarQuantity + 2*twoStarQuantity + 3*threeStarQuantity 
				+ 4*fourStarQuantity + 5*fiveStarQuantity)/commentQuantity;
		ProductRateDTO dto = new ProductRateDTO();
		dto.setAverageStar(averageStar);
		dto.setCommentQuantity(commentQuantity);
		dto.setOneStarQuantity(oneStarQuantity);
		dto.setTwoStarQuantity(twoStarQuantity);
		dto.setThreeStarQuantity(threeStarQuantity);
		dto.setFourStarQuantity(fourStarQuantity);
		dto.setFiveStarQuantity(fiveStarQuantity);
		oneStarQuantity = 0;
		twoStarQuantity = 0;
		threeStarQuantity = 0;
		fourStarQuantity = 0;
		fiveStarQuantity = 0;
		averageStar = 0f;
		commentQuantity = 0;
		return dto;
	}

}
