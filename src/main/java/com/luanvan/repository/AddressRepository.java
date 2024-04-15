package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.AddressEntity;

public interface AddressRepository  extends JpaRepository<AddressEntity, Long> {
	@Query("SELECT a FROM AddressEntity a WHERE a.user.id = ?1 AND a.removed = false")
	List<AddressEntity> findAll(Long userId);
}
