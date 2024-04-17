package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.OrderItemConverter;
import com.luanvan.dto.OrderItemDTO;
import com.luanvan.entity.CartItemEntity;
import com.luanvan.entity.ProductDetailSizeEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.CartItemRepository;
import com.luanvan.repository.OrderItemRepository;
import com.luanvan.repository.ProductDetailSizeRepository;
import com.luanvan.repository.ProductRepository;
import com.luanvan.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private OrderItemConverter orderItemConverter;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private ProductDetailSizeRepository productDetailSizeRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public boolean save(OrderItemDTO orderItem) {
		if(orderItemRepository.save(orderItemConverter.toEntity(orderItem)) != null) {
			CartItemEntity cartItemEntity = cartItemRepository.findById(orderItem.getCartItemId()).get();
			ProductDetailSizeEntity productDetailSizeEntity = cartItemEntity.getProduct();
			productDetailSizeEntity.setQuantity(productDetailSizeEntity.getQuantity() - orderItem.getQuantity());
			productDetailSizeRepository.save(productDetailSizeEntity);
			ProductEntity productEntity = productDetailSizeEntity.getProductDetail().getProduct();
			productEntity.setSoldQuantity(productEntity.getSoldQuantity() + orderItem.getQuantity());
			productRepository.save(productEntity);
			cartItemRepository.deleteById(orderItem.getCartItemId());
			return true;
		}
		return false;
	}

}
