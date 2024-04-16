package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.OrderDTO;

public interface OrderService {
	Long save(OrderDTO order);
	boolean update(OrderDTO order);
	List<OrderDTO> findAll();
	List<OrderDTO> findAll(int page, int limit);
	List<OrderDTO> findAll(int page, int limit, String strStatus);
	List<OrderDTO> findAll(Long userId);
	List<OrderDTO> findAll(Long userId, String strStatus);
	int totalItem();
	int totalItem(String strStatus);
	OrderDTO findById(Long id);
}
