package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.OrderEntity;
import com.luanvan.enums.OrderStatus;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
	@Query(value = "SELECT * FROM orders c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<OrderEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM orders c WHERE c.status = ?1 LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<OrderEntity> findAll(OrderStatus status, int page, int limit);
	
	@Query("SELECT c FROM OrderEntity c WHERE c.address.user.id = ?1 ORDER BY c.createdTime DESC")
	List<OrderEntity> findAllByUserId(Long userId);
	
	@Query("SELECT c FROM OrderEntity c WHERE c.address.user.id = ?1 AND c.status = ?2 ORDER BY c.createdTime DESC")
	List<OrderEntity> findAllByUserIdAndStatus(Long userId, OrderStatus status);
	
	@Query(value = "SELECT * FROM orders c WHERE c.status = ?1", nativeQuery = true)
	List<OrderEntity> findAll(OrderStatus status);
	
	
}
