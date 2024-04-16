package com.luanvan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.luanvan.enums.OrderStatus;
import com.luanvan.enums.PaymentMethod;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@CreationTimestamp
	private Date createdTime;
	@UpdateTimestamp
	private Date updatedTime;
	private OrderStatus status;
	private String note;
	private PaymentMethod payment;
	private String deliveryImage;
	private double discountVoucher;
	private double total;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "shipper_id")
	private UserEntity shipper;
	
	@ManyToOne(optional = true)
	@JoinColumn(name = "voucher_id")
	private VoucherEntity voucher;
	
	@ManyToOne
	@JoinColumn(name = "shipping_id")
	private ShippingEntity shipping;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", orphanRemoval = true)
	private List<OrderItemEntity> orderItems = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "address_id")
	private AddressEntity address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public PaymentMethod getPayment() {
		return payment;
	}

	public void setPayment(PaymentMethod payment) {
		this.payment = payment;
	}

	public String getDeliveryImage() {
		return deliveryImage;
	}

	public void setDeliveryImage(String deliveryImage) {
		this.deliveryImage = deliveryImage;
	}

	public UserEntity getShipper() {
		return shipper;
	}

	public void setShipper(UserEntity shipper) {
		this.shipper = shipper;
	}

	public VoucherEntity getVoucher() {
		return voucher;
	}

	public void setVoucher(VoucherEntity voucher) {
		this.voucher = voucher;
	}

	public ShippingEntity getShipping() {
		return shipping;
	}

	public void setShipping(ShippingEntity shipping) {
		this.shipping = shipping;
	}

	public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
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
	
	
}
