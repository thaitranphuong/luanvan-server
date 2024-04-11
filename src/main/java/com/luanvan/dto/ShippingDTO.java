package com.luanvan.dto;

public class ShippingDTO {
	private Long id;
	private String name;
	private double cost;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public boolean isRemoved() {
		return isRemoved;
	}
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	
}
