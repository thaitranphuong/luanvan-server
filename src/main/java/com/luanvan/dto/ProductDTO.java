package com.luanvan.dto;

import java.util.List;

public class ProductDTO {
	private Long id;
	private String name;
	private String shortDescription;
	private String fullDescription;
	private String material;
	private String origin;
	private String thumbnail;
	private int soldQuantity = 0;
	private int percentDiscount = 0;
	private boolean enabled = true;
	
	private Long categoryId;
	private String categoryName;
	private Long brandId;
	private String brandName;
	
	private List<ProductDetailDTO> listProductDetail;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getFullDescription() {
		return fullDescription;
	}
	public void setFullDescription(String fullDescription) {
		this.fullDescription = fullDescription;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getSoldQuantity() {
		return soldQuantity;
	}
	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
	public int getPercentDiscount() {
		return percentDiscount;
	}
	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public List<ProductDetailDTO> getListProductDetail() {
		return listProductDetail;
	}
	public void setListProductDetail(List<ProductDetailDTO> listProductDetail) {
		this.listProductDetail = listProductDetail;
	}
	
	
	
}
