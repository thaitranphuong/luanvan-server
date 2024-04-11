package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ProductDetailEntity;

public interface ProductDetailRepository extends JpaRepository<ProductDetailEntity, Long>{
	@Query("SELECT p FROM ProductDetailEntity p WHERE p.product.id = ?1")
	List<ProductDetailEntity> findAllByProductId(Long productId);
}
