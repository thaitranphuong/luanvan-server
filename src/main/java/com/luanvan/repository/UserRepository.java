package com.luanvan.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
//	@Query("SELECT a FROM AnswerEntity a WHERE a.implementation.id = ?1")
//	List<AnswerEntity> findAllByImplementationId(Long id);
	
	Optional<UserEntity> findByEmail(String email);
}
