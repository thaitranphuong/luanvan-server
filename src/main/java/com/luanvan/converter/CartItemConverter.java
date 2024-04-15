package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.CartItemDTO;
import com.luanvan.entity.CartItemEntity;
import com.luanvan.repository.ProductDetailSizeRepository;
import com.luanvan.repository.UserRepository;

@Component
public class CartItemConverter {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductDetailSizeRepository productDetailSizeRepository;
	
	public CartItemEntity toEntity(CartItemDTO dto) {
		CartItemEntity entity = new CartItemEntity();
		entity.setQuantity(dto.getQuantity());
		entity.setProduct(productDetailSizeRepository.findById(dto.getProductId()).get());
		entity.setUser(userRepository.findById(dto.getUserId()).get());
		return entity;
	}
	
	public CartItemDTO toDTO(CartItemEntity entity) {
		CartItemDTO dto = new CartItemDTO();
		dto.setId(entity.getId());
		dto.setQuantity(entity.getQuantity());
		dto.setProductName(entity.getProduct().getProductDetail().getProduct().getName());
		dto.setProductColor(entity.getProduct().getProductDetail().getColor());
		dto.setProductSize(entity.getProduct().getSize());
		dto.setProductImage(entity.getProduct().getProductDetail().getImage());
		dto.setProductId(entity.getProduct().getId());
		dto.setUserId(entity.getUser().getId());
		dto.setProductPrice(entity.getProduct().getProductDetail().getPrice() * 
				(100 - entity.getProduct().getProductDetail().getProduct().getPercentDiscount()) / 100 );
		dto.setProductQuantity(entity.getProduct().getQuantity());
		return dto;
	}
	

	public CartItemEntity toEntity(CartItemDTO dto, CartItemEntity entity) {
		entity.setQuantity(dto.getQuantity());
		return entity;
	}
}
