package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.OrderConverter;
import com.luanvan.dto.OrderDTO;
import com.luanvan.entity.OrderEntity;
import com.luanvan.enums.OrderStatus;
import com.luanvan.repository.OrderRepository;
import com.luanvan.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Long save(OrderDTO order) {
		return orderRepository.save(orderConverter.toEntity(order)).getId();
	}

	@Override
	public boolean update(OrderDTO order) {
		return orderRepository.save(orderConverter.toEntity(order,
				orderRepository.findById(order.getId()).get())) != null;
	}

	@Override
	public List<OrderDTO> findAll() {
		List<OrderEntity> entities = orderRepository.findAll();
		List<OrderDTO> DTOs = new ArrayList<>();
		for (OrderEntity item : entities) {
			OrderDTO dto = orderConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<OrderDTO> findAll(int page, int limit) {
		List<OrderEntity> entities = orderRepository.findAll(page, limit);
		List<OrderDTO> DTOs = new ArrayList<>();
		for(OrderEntity item: entities) {
			OrderDTO dto = orderConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<OrderDTO> findAll(int page, int limit, String strStatus) {
		OrderStatus status = OrderStatus.PENDING;
		if(strStatus.equals("1")) status = OrderStatus.PREPARING;
		else if(strStatus.equals("2")) status = OrderStatus.DELIVERY;
		else if(strStatus.equals("3")) status = OrderStatus.SUCCESS;
		else if(strStatus.equals("4")) status = OrderStatus.CANCEL;
		List<OrderEntity> entities = orderRepository.findAll(status, page, limit);
		List<OrderDTO> DTOs = new ArrayList<>();
		for(OrderEntity item: entities) {
			OrderDTO dto = orderConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<OrderDTO> findAll(Long userId) {
		List<OrderEntity> entities = orderRepository.findAllByUserId(userId);
		List<OrderDTO> DTOs = new ArrayList<>();
		for(OrderEntity item: entities) {
			OrderDTO dto = orderConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<OrderDTO> findAll(Long userId, String strStatus) {
		OrderStatus status = OrderStatus.PENDING;
		if(strStatus.equals("1")) status = OrderStatus.PREPARING;
		else if(strStatus.equals("2")) status = OrderStatus.DELIVERY;
		else if(strStatus.equals("3")) status = OrderStatus.SUCCESS;
		else if(strStatus.equals("4")) status = OrderStatus.CANCEL;
		List<OrderEntity> entities = orderRepository.findAllByUserIdAndStatus(userId, status);
		List<OrderDTO> DTOs = new ArrayList<>();
		for(OrderEntity item: entities) {
			OrderDTO dto = orderConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<OrderEntity> result = orderRepository.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String strStatus) {
		OrderStatus status = OrderStatus.PENDING;
		if(strStatus.equals("1")) status = OrderStatus.PREPARING;
		else if(strStatus.equals("2")) status = OrderStatus.DELIVERY;
		else if(strStatus.equals("3")) status = OrderStatus.SUCCESS;
		else if(strStatus.equals("4")) status = OrderStatus.CANCEL;
		List<OrderEntity> result = orderRepository.findAll(status);
		return result.size();
	}

	@Override
	public OrderDTO findById(Long id) {
		OrderEntity OrderEntity = orderRepository.findById(id).orElse(null);
		OrderDTO dto;
		if(OrderEntity != null) {
			dto = orderConverter.toDTO(OrderEntity);
		} else {
			dto = new OrderDTO();
		}
		return dto;
	}

}
