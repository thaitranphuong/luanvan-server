package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<ProductEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM products c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAll(String name, int page, int limit);
}
