package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ShippingEntity;

public interface ShippingRepository  extends JpaRepository<ShippingEntity, Long>{
	@Query(value = "SELECT * FROM shippings c WHERE c.is_removed = false", nativeQuery = true)
	List<ShippingEntity> findAll();
	
	@Query(value = "SELECT * FROM shippings c WHERE c.name LIKE %?1% and c.is_removed = false", nativeQuery = true)
	List<ShippingEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM shippings c WHERE c.is_removed = false LIMIT ?2 OFFSET ?1 ", nativeQuery = true)
	List<ShippingEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM shippings c WHERE c.name LIKE %?1% and c.is_removed = false LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ShippingEntity> findAll(String name, int page, int limit);
}
