package com.luanvan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luanvan.entity.OrderItemEntity;

public interface OrderItemRepository  extends JpaRepository<OrderItemEntity, Long>{

}
