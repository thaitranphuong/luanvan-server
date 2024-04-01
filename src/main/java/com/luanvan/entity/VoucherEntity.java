package com.luanvan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.luanvan.enums.VoucherCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "vouchers")
public class VoucherEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private double _index;
	private int quantity;
	private int remainingQuanity;
	private Date startTime;
	private Date endTime;
	private VoucherCategory category;
	private boolean isRemoved;
	
	@ManyToMany(mappedBy = "vouchers")
	private Set<UserEntity> users = new HashSet<>();
	
	@OneToMany(mappedBy = "voucher")
	private List<OrderEntity> orders = new ArrayList<>();

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

	public double getIndex() {
		return _index;
	}

	public void setIndex(double index) {
		this._index = index;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getRemainingQuanity() {
		return remainingQuanity;
	}

	public void setRemainingQuanity(int remainingQuanity) {
		this.remainingQuanity = remainingQuanity;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public VoucherCategory getCategory() {
		return category;
	}

	public void setCategory(VoucherCategory category) {
		this.category = category;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}
	
	
}
