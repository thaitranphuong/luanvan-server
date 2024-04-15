package com.luanvan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.luanvan.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	//Admin page
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1%", nativeQuery = true)
	List<ProductEntity> findAll(String name);
	
	@Query(value = "SELECT * FROM products c LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAll(int page, int limit);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAll(String name, int page, int limit); //
	
	//Customer page
	@Query(value = "SELECT * FROM products c WHERE c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllCustomerPage();
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllCustomerPage(String name);
	
	@Query(value = "SELECT * FROM products c WHERE c.enabled = true LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAllCustomerPage(int page, int limit);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.enabled = true LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAllCustomerPage(String name, int page, int limit); //
	
	@Query(value = "SELECT * FROM products c WHERE c.category_id = ?3 AND c.brand_id = ?4 AND c.enabled = true LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAllByCategoryAndBrand(int page, int limit, String strCategoryId, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.category_id = ?4 AND c.brand_id = ?5 AND c.enabled = true LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAllByCategoryAndBrand(String name, int page, int limit, String strCategoryId, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.category_id = ?1 AND c.brand_id = ?2 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByCategoryAndBrand(String strCategoryId, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.category_id = ?2 AND c.brand_id = ?3 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByCategoryAndBrand(String name, String strCategoryId, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.brand_id = ?3 AND c.enabled = true LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAllByBrand(int page, int limit, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.brand_id = ?4 AND c.enabled = true LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAllByBrand(String name, int page, int limit, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.brand_id = ?1 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByBrand(String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.brand_id = ?2 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByBrand(String name, String strBrandId);
	
	@Query(value = "SELECT * FROM products c WHERE c.category_id = ?3 AND c.enabled = true LIMIT ?2 OFFSET ?1", nativeQuery = true)
	List<ProductEntity> findAllByCategory(int page, int limit, String strCategoryId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.category_id = ?4 AND c.enabled = true LIMIT ?3 OFFSET ?2", nativeQuery = true)
	List<ProductEntity> findAllByCategory(String name, int page, int limit, String strCategoryId);
	
	@Query(value = "SELECT * FROM products c WHERE c.category_id = ?1 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByCategory(String strCategoryId);
	
	@Query(value = "SELECT * FROM products c WHERE c.name LIKE %?1% AND c.category_id = ?2 AND c.enabled = true", nativeQuery = true)
	List<ProductEntity> findAllByCategory(String name, String strCategoryId);
	
	@Query(value = "SELECT * FROM products c WHERE c.enabled = true ORDER BY c.sold_quantity DESC LIMIT 6", nativeQuery = true)
	List<ProductEntity> findBestSales();
	
	@Query(value = "SELECT * FROM products c WHERE c.enabled = true LIMIT 6", nativeQuery = true)
	List<ProductEntity> findNewProducts();
}
