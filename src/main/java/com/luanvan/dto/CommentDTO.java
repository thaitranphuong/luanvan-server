package com.luanvan.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

public class CommentDTO {
	private Long id;
	private String content;
	private int star = 5;
	private String image;
	private String createdTime;
	private Long userId;
	private String userName;
	private String userAvatar;
	private Long productId;
	private Long blogId;
	private Set<Long> userLikeIds;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		this.createdTime = formatter.format(createdTime);
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getBlogId() {
		return blogId;
	}
	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public Set<Long> getUserLikeIds() {
		return userLikeIds;
	}
	public void setUserLikeIds(Set<Long> userLikeIds) {
		this.userLikeIds = userLikeIds;
	}
	
	
}
