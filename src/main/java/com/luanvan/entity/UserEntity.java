package com.luanvan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;
	private String name;
	private String phone;
	private String address;
	private Date birthday = new Date();
	private boolean gender;
	private String avatar;
	private String password;
	private boolean enabled = true;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleEntity role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<NotificationEntity> notifications = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sender", orphanRemoval = true)
	private List<MessageEntity> messagesSend = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "receiver", orphanRemoval = true)
	private List<MessageEntity> messagesReceive = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_voucher",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "voucher_id"))
	private Set<VoucherEntity> vouchers = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<CartItemEntity> cartItems = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<AddressEntity> addresses = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<OrderEntity> orders = new ArrayList<>();
	
	@OneToMany(mappedBy = "shipper")
	private List<OrderEntity> ordersShipping = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
	private List<CommentEntity> comments = new ArrayList<>();
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(name = "user_liked_comment",
				joinColumns = @JoinColumn(name = "user_id"),
				inverseJoinColumns = @JoinColumn(name = "comment_id"))
	private Set<CommentEntity> likedComments = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
	private List<BlogEntity> blogs = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true)
	private List<ImportEntity> imports = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<NotificationEntity> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<NotificationEntity> notifications) {
		this.notifications = notifications;
	}

	public List<MessageEntity> getMessagesSend() {
		return messagesSend;
	}

	public void setMessagesSend(List<MessageEntity> messagesSend) {
		this.messagesSend = messagesSend;
	}

	public List<MessageEntity> getMessagesReceive() {
		return messagesReceive;
	}

	public void setMessagesReceive(List<MessageEntity> messagesReceive) {
		this.messagesReceive = messagesReceive;
	}

	public Set<VoucherEntity> getVouchers() {
		return vouchers;
	}

	public void setVouchers(Set<VoucherEntity> vouchers) {
		this.vouchers = vouchers;
	}

	public List<CartItemEntity> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemEntity> cartItems) {
		this.cartItems = cartItems;
	}

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
		this.addresses = addresses;
	}

	public List<OrderEntity> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderEntity> orders) {
		this.orders = orders;
	}

	public List<OrderEntity> getOrdersShipping() {
		return ordersShipping;
	}

	public void setOrdersShipping(List<OrderEntity> ordersShipping) {
		this.ordersShipping = ordersShipping;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public Set<CommentEntity> getLikedComments() {
		return likedComments;
	}

	public void setLikedComments(Set<CommentEntity> likedComments) {
		this.likedComments = likedComments;
	}

	public List<BlogEntity> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<BlogEntity> blogs) {
		this.blogs = blogs;
	}

	public List<ImportEntity> getImports() {
		return imports;
	}

	public void setImports(List<ImportEntity> imports) {
		this.imports = imports;
	}
	
	

}
