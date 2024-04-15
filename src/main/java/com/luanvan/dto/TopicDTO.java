package com.luanvan.dto;

public class TopicDTO {
	private Long id;
	private String name;
	private String code;
	private int blogQuantity;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getBlogQuantity() {
		return blogQuantity;
	}
	public void setBlogQuantity(int blogQuantity) {
		this.blogQuantity = blogQuantity;
	}
	
	
}
