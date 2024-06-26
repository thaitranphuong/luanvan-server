package com.luanvan.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageDTO {
	private Long id;
	private String content;
	private boolean isRead = false;
	private String createdTime;
	private Long senderId;
	private String senderName;
	private String avatar;
	private Long receiverId;
	
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
	public boolean isRead() {
		return isRead;
	}
	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		this.createdTime = formatter.format(createdTime);
	}
	public Long getSenderId() {
		return senderId;
	}
	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}
	public Long getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
}
