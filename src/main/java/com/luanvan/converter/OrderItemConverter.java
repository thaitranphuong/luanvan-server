package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.OrderItemDTO;
import com.luanvan.entity.OrderItemEntity;
import com.luanvan.repository.OrderRepository;

@Component
public class OrderItemConverter {
	@Autowired
	private OrderRepository orderRepository;
	
	public OrderItemEntity toEntity(OrderItemDTO dto) {
		OrderItemEntity entity = new OrderItemEntity();
		entity.setName(dto.getName());
		entity.setImage(dto.getImage());
		entity.setColor(dto.getColor());
		entity.setPrice(dto.getPrice());
		entity.setSize(dto.getSize());
		entity.setQuantity(dto.getQuantity());
		entity.setOrder(orderRepository.findById(dto.getOrderId()).get());
		return entity;
	}
	
	public OrderItemDTO toDTO(OrderItemEntity entity) {
		OrderItemDTO dto = new OrderItemDTO();
		dto.setId(entity.getId());
		dto.setImage(entity.getImage());
		dto.setName(entity.getName());
		dto.setColor(entity.getColor());
		dto.setSize(entity.getSize());
		dto.setQuantity(entity.getQuantity());
		dto.setPrice(entity.getPrice());
		return dto;
	}
}
