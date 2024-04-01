package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.SupplierEntity;

public interface SupplierRepository  extends JpaRepository<SupplierEntity, Long>{
	@Query(value = "SELECT * FROM suppliers c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<SupplierEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM suppliers c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<SupplierEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM suppliers c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<SupplierEntity> findAll(String name, int page, int limit);
}
