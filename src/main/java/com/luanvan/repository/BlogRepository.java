package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.BlogEntity;

public interface BlogRepository  extends JpaRepository<BlogEntity, Long> {
	@Query(value = "SELECT * FROM blogs c WHERE c.title LIKE %?1%", nativeQuery = true)
	List<BlogEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM blogs c WHERE c.title LIKE %?1% and c.topic_id = ?1", nativeQuery = true)
	List<BlogEntity> findAll(Long topicId);
	
	@Query(value = "SELECT * FROM blogs c WHERE c.title LIKE %?1% and c.topic_id = ?2", nativeQuery = true)
	List<BlogEntity> findAll(String name, Long topicId);
	
	@Query(value = "SELECT * FROM blogs c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<BlogEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM blogs c WHERE c.title LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<BlogEntity> findAll(String name, int page, int limit);
	
	@Query(value = "SELECT * FROM blogs c WHERE c.topic_id = ?3 LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<BlogEntity> findAll(int page, int limit, Long topicId);
	
	@Query(value = "SELECT * FROM blogs c WHERE c.title LIKE %?1% and c.topic_id = ?4 LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<BlogEntity> findAll(String name, int page, int limit, Long topicId);
}
