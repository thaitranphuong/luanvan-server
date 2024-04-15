package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.CartItemEntity;

public interface CartItemRepository extends JpaRepository<CartItemEntity, Long>{
	@Query("SELECT c FROM CartItemEntity c WHERE c.user.id = ?1")
	List<CartItemEntity> findByCustomerId(Long customerId);
	
	@Query("SELECT c FROM CartItemEntity c WHERE c.user.id = ?1 AND c.product.id = ?2")
	CartItemEntity findByCustomerIdAndProductId(Long customerId, Long productId);
}
