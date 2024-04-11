package com.luanvan.dto;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailDTO {
	private Long id;
	private String image;
	private String color;
	private double price;
	private Long productId;
	private List<ProductDetailSizeDTO> listProductDetailSizes = new ArrayList<>();
	
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
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public List<ProductDetailSizeDTO> getListProductDetailSizes() {
		return listProductDetailSizes;
	}
	public void setListProductDetailSizes(List<ProductDetailSizeDTO> listProductDetailSizeDTO) {
		this.listProductDetailSizes = listProductDetailSizeDTO;
	}
	
	
}
