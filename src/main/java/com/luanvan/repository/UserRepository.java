package com.luanvan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.SupplierEntity;
import com.luanvan.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	Optional<UserEntity> findByEmail(String email);
	
	@Query("SELECT u FROM UserEntity u WHERE u.role.name = ?1")
	Optional<List<UserEntity>> findByRoleName(String role);
	
	@Query(value = "SELECT * FROM users c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<UserEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM users c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<UserEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM users c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<UserEntity> findAll(String name, int page, int limit);
}
