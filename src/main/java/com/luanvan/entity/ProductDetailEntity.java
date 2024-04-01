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
@Table(name = "product_details")
public class ProductDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String image;
	private String color;
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private ProductEntity product;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "productDetail", orphanRemoval = true)
	private List<ProductDetailSizeEntity> productDetailSizes = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public List<ProductDetailSizeEntity> getProductDetailSizes() {
		return productDetailSizes;
	}

	public void setProductDetailSizes(List<ProductDetailSizeEntity> productDetailSizes) {
		this.productDetailSizes = productDetailSizes;
	}
	
	
}
