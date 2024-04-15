package com.luanvan.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class VoucherDTO {
	private Long id;
	private String name;
	private double _index;
	private double maxDiscount;
	private int quantity;
	private int remainingQuantity = quantity;
	private String startTime;
	private String endTime;
	private boolean category;
	private boolean isRemoved = false;
	
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
	public double get_index() {
		return _index;
	}
	public void set_index(double _index) {
		this._index = _index;
	}
	public double getMaxDiscount() {
		return maxDiscount;
	}
	public void setMaxDiscount(double maxDiscount) {
		this.maxDiscount = maxDiscount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.startTime = formatter.format(startTime);
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.endTime = formatter.format(endTime);
	}
	public boolean isCategory() {
		return category;
	}
	public void setCategory(boolean category) {
		this.category = category;
	}
	public boolean isRemoved() {
		return isRemoved;
	}
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	public int getRemainingQuantity() {
		return remainingQuantity;
	}
	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
