package com.luanvan.dto;

public class ImportDetailDTO {
	private Long id;	
	private int quantity;
	private double price;
	private Long productDetailSizeId;
	private Long importId;
	private String productName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Long getProductDetailSizeId() {
		return productDetailSizeId;
	}
	public void setProductDetailSizeId(Long productDetailSizeId) {
		this.productDetailSizeId = productDetailSizeId;
	}
	public Long getImportId() {
		return importId;
	}
	public void setImportId(Long importId) {
		this.importId = importId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	
	
	
}
