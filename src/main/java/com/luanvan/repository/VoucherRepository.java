package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.VoucherEntity;

public interface VoucherRepository extends JpaRepository<VoucherEntity, Long>{
	@Query(value = "SELECT * FROM vouchers c WHERE c.is_removed = false", nativeQuery = true)
	List<VoucherEntity> findAll();
	
	@Query(value = "SELECT * FROM vouchers c WHERE c.name LIKE %?1% and c.is_removed = false", nativeQuery = true)
	List<VoucherEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM vouchers c WHERE c.is_removed = false and c.id not in "
			+ "(SELECT c.id FROM vouchers c LEFT JOIN user_voucher sc "
			+ "ON c.id = sc.voucher_id WHERE sc.user_id = ?1)",
				nativeQuery = true)
	List<VoucherEntity> findAll(Long userId);
	
	@Query(value = "SELECT * FROM vouchers c WHERE c.is_removed = false LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<VoucherEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM vouchers c WHERE c.name LIKE %?1% and c.is_removed = false LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<VoucherEntity> findAll(String name, int page, int limit);
	
	@Query(value = "SELECT * FROM vouchers c WHERE c.is_removed = false and c.id not in "
			+ "(SELECT c.id FROM vouchers c LEFT JOIN user_voucher sc "
			+ "ON c.id = sc.voucher_id WHERE sc.user_id = ?1) LIMIT ?3 OFFSET ?2",
				nativeQuery = true)
	List<VoucherEntity> findAll(Long userId, int page, int limit);
	
	VoucherEntity findByName(String name);
	
	@Query(value = "SELECT * FROM vouchers c LEFT JOIN user_voucher sc "
			+ "ON c.id = sc.voucher_id WHERE sc.user_id = ?1 and c.is_removed = false",
				nativeQuery = true)
	List<VoucherEntity> findAllByUserId(Long userId);
}
