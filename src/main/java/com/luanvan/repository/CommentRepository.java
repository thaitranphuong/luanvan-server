package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Long>{
	@Query(value = "SELECT * FROM comments c WHERE c.blog_id = ?1", nativeQuery = true)
	List<CommentEntity> findAllByBlogId(String strBlogId);
	
	@Query(value = "SELECT * FROM comments c WHERE c.product_id = ?1", nativeQuery = true)
	List<CommentEntity> findAllByProductId(String strProductId);
	
	@Query(value = "SELECT * FROM comments c WHERE c.blog_id = ?1 ORDER BY c.created_time DESC LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<CommentEntity> findAllByBlogId(String strBlogId, int page, int limit);
	
	@Query(value = "SELECT * FROM comments c WHERE c.product_id = ?1 ORDER BY c.created_time DESC LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<CommentEntity> findAllByProductId(String strProductId, int page, int limit);
}
