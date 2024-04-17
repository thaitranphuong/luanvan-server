package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.CartItemConverter;
import com.luanvan.dto.CartItemDTO;
import com.luanvan.entity.CartItemEntity;
import com.luanvan.repository.CartItemRepository;
import com.luanvan.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartItemConverter cartItemConverter;
	
	@Override
	public boolean save(CartItemDTO cartItem) {
		CartItemEntity entity = cartItemRepository.findByCustomerIdAndProductId(cartItem.getUserId(), cartItem.getProductId());
		if(entity != null) {
			entity.setQuantity(entity.getQuantity() + cartItem.getQuantity());
			return cartItemRepository.save(entity) != null;
		}
		return cartItemRepository.save(cartItemConverter.toEntity(cartItem)) != null;
	}

	@Override
	public boolean update(CartItemDTO cartItem) {
		return cartItemRepository.save(cartItemConverter.toEntity(cartItem,
				cartItemRepository.findById(cartItem.getId()).get())) != null;
	}

	@Override
	public boolean delete(Long id) {
		cartItemRepository.deleteById(id);
		return true;
	}

	@Override
	public List<CartItemDTO> findByCustomerId(Long customerId) {
		List<CartItemEntity> entities = cartItemRepository.findByCustomerId(customerId);
		List<CartItemDTO> DTOs = new ArrayList<>();
		entities.forEach(item -> {
			DTOs.add(cartItemConverter.toDTO(item));
		});
		return DTOs;
	}

}
