package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ProductDetailSizeEntity;

import jakarta.transaction.Transactional;

public interface ProductDetailSizeRepository extends JpaRepository<ProductDetailSizeEntity, Long>{
	@Query("SELECT p FROM ProductDetailSizeEntity p WHERE p.productDetail.id = ?1")
	List<ProductDetailSizeEntity> findAllByProductDetailId(Long productDetailId);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM product_detail_sizes WHERE product_detail_id = ?1", nativeQuery = true)
	void deleteByProductDetailId(Long productDetailId);
}
