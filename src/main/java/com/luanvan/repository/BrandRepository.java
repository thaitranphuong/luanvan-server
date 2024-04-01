package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.BrandEntity;

public interface BrandRepository  extends JpaRepository<BrandEntity, Long>{
	@Query(value = "SELECT * FROM brands c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<BrandEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM brands c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<BrandEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM brands c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<BrandEntity> findAll(String name, int page, int limit);
}
