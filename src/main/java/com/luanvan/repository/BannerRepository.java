package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.BannerEntity;

public interface BannerRepository  extends JpaRepository<BannerEntity, Long> {
	@Query(value = "SELECT * FROM banners c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<BannerEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM banners c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<BannerEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM banners c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<BannerEntity> findAll(String name, int page, int limit);
}
