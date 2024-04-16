package com.luanvan.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDTO {
	private Long id;
	private String createdTime;
	private int status;
	private String note;
	private String payment;
	private String deliveryImage;
	private double discountVoucher;
	private double total;
	private Long shipperId;
	private Long voucherId;
	private Long shippingId;
	private Long addressId;
	
	private String username;
	private String phone;
	private String shipperName;
	private String addressName;
	private String addressFull;
	private String shippingName;
	private double shippingCost;
	private String voucherName;
	private List<OrderItemDTO> orderItems = new ArrayList<>(); 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
		this.createdTime = formatter.format(createdTime);
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getDeliveryImage() {
		return deliveryImage;
	}
	public void setDeliveryImage(String deliveryImage) {
		this.deliveryImage = deliveryImage;
	}
	public Long getShipperId() {
		return shipperId;
	}
	public void setShipperId(Long shipperId) {
		this.shipperId = shipperId;
	}
	public Long getVoucherId() {
		return voucherId;
	}
	public void setVoucherId(Long voucherId) {
		this.voucherId = voucherId;
	}
	public Long getShippingId() {
		return shippingId;
	}
	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public double getDiscountVoucher() {
		return discountVoucher;
	}
	public void setDiscountVoucher(double discountVoucher) {
		this.discountVoucher = discountVoucher;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressFull() {
		return addressFull;
	}
	public void setAddressFull(String addressFull) {
		this.addressFull = addressFull;
	}
	public String getShippingName() {
		return shippingName;
	}
	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}
	public double getShippingCost() {
		return shippingCost;
	}
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}
	public String getVoucherName() {
		return voucherName;
	}
	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}
	public List<OrderItemDTO> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemDTO> orderItems) {
		this.orderItems = orderItems;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
