package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ImportEntity;

public interface ImportRepository extends JpaRepository<ImportEntity, Long>{
	@Query(value = "SELECT * FROM imports c WHERE c.id LIKE %?1%", nativeQuery = true)
	List<ImportEntity> findAll(Long id);
	
	@Query(value = "SELECT * FROM imports c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ImportEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM imports c WHERE c.id LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ImportEntity> findAll(Long id, int page, int limit);
}
