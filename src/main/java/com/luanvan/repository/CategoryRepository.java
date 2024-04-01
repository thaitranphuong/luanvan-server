package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	@Query(value = "SELECT * FROM categories c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<CategoryEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM categories c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<CategoryEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM categories c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<CategoryEntity> findAll(String name, int page, int limit);
}
