package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.CartItemDTO;

public interface CartItemService {
	boolean save(CartItemDTO cartItem);
	boolean update(CartItemDTO cartItem);
	boolean delete(Long id);
	List<CartItemDTO> findByCustomerId(Long customerId);
}
