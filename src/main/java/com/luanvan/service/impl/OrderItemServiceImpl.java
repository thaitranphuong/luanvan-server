package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.OrderItemConverter;
import com.luanvan.dto.OrderItemDTO;
import com.luanvan.repository.OrderItemRepository;
import com.luanvan.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemConverter orderItemConverter;

	@Override
	public boolean save(OrderItemDTO orderItem) {
		return orderItemRepository.save(orderItemConverter.toEntity(orderItem)) != null;
	}

}
