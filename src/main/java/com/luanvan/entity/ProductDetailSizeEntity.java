package com.luanvan.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "product_detail_sizes")
public class ProductDetailSizeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String size;
	private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_detail_id")
	private ProductDetailEntity productDetail;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productDetailSize", orphanRemoval = true)
	private List<ImportDetailEntity> importDetails = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public ProductDetailEntity getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductDetailEntity productDetail) {
		this.productDetail = productDetail;
	}

	public List<ImportDetailEntity> getImportDetails() {
		return importDetails;
	}

	public void setImportDetails(List<ImportDetailEntity> importDetails) {
		this.importDetails = importDetails;
	}
	
	
}
