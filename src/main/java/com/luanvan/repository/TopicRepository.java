package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.SupplierEntity;
import com.luanvan.entity.TopicEntity;

public interface TopicRepository extends JpaRepository<TopicEntity, Long>{
	@Query(value = "SELECT * FROM topics c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<TopicEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM topics c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<TopicEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM topics c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<TopicEntity> findAll(String name, int page, int limit);
}
