package com.luanvan.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.OrderDTO;
import com.luanvan.dto.OrderItemDTO;
import com.luanvan.entity.AddressEntity;
import com.luanvan.entity.OrderEntity;
import com.luanvan.entity.OrderItemEntity;
import com.luanvan.entity.ShippingEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.entity.VoucherEntity;
import com.luanvan.enums.OrderStatus;
import com.luanvan.enums.PaymentMethod;
import com.luanvan.repository.AddressRepository;
import com.luanvan.repository.ShippingRepository;
import com.luanvan.repository.UserRepository;
import com.luanvan.repository.VoucherRepository;

@Component
public class OrderConverter {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ShippingRepository shippingRepository;
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private OrderItemConverter orderItemConverter;
	
	public OrderEntity toEntity(OrderDTO dto) {
		OrderEntity entity = new OrderEntity();
		OrderStatus status = OrderStatus.PENDING;
		if(dto.getStatus() == 1) status = OrderStatus.PREPARING;
		else if(dto.getStatus() == 2) status = OrderStatus.DELIVERY;
		else if(dto.getStatus() == 3) status = OrderStatus.SUCCESS;
		else if(dto.getStatus() == 4) status = OrderStatus.CANCEL;
		entity.setStatus(status);
		entity.setNote(dto.getNote());
		PaymentMethod payment = PaymentMethod.COD;
		if (dto.getPayment().equals("VNPAY")) payment = PaymentMethod.VNPAY;
		else if (dto.getPayment().equals("PAYPAL")) payment = PaymentMethod.PAYPAL;
		entity.setPayment(payment);
		entity.setDeliveryImage(dto.getDeliveryImage());
		entity.setDiscountVoucher(dto.getDiscountVoucher());
		entity.setTotal(dto.getTotal());
		if(dto.getShipperId() != null) {
			UserEntity shipper = userRepository.findById(dto.getShipperId()).get();
			entity.setShipper(shipper);
		}
		AddressEntity address = addressRepository.findById(dto.getAddressId()).get();
		entity.setAddress(address);
		ShippingEntity shipping = shippingRepository.findById(dto.getShippingId()).get();
		entity.setShipping(shipping);
		if(dto.getVoucherId() != null) {
			VoucherEntity voucher = voucherRepository.findById(dto.getVoucherId()).get();
			entity.setVoucher(voucher);
		}
		return entity;
	}
	
	public OrderDTO toDTO(OrderEntity entity) {
		OrderDTO dto = new OrderDTO();
		dto.setId(entity.getId());
		dto.setCreatedTime(entity.getCreatedTime());
		int status = 0;
		if(entity.getStatus() == OrderStatus.PREPARING) status = 1;
		else if(entity.getStatus() == OrderStatus.DELIVERY) status = 2;
		else if(entity.getStatus() == OrderStatus.SUCCESS) status = 3;
		else if(entity.getStatus() == OrderStatus.CANCEL) status = 4;
		dto.setStatus(status);
		dto.setNote(entity.getNote());
		String payment = "COD";
		if (entity.getPayment() == PaymentMethod.VNPAY) payment = "VNPAY";
		else if (entity.getPayment() == PaymentMethod.PAYPAL) payment = "PAYPAL";
		dto.setPayment(payment);
		dto.setDeliveryImage(entity.getDeliveryImage());
		dto.setDiscountVoucher(entity.getDiscountVoucher());
		dto.setTotal(entity.getTotal());
		dto.setUsername(entity.getAddress().getUser().getName());
		dto.setPhone(entity.getAddress().getPhone());
		dto.setAddressName(entity.getAddress().getUsername());
		dto.setAddressFull(entity.getAddress().getSpecification() + ", " + entity.getAddress().getWard() + ", " +
									entity.getAddress().getDistrict() + ", " + entity.getAddress().getCity());
		dto.setShippingName(entity.getShipping().getName());
		dto.setShippingCost(entity.getShipping().getCost());
		if(entity.getShipper() != null) {
			dto.setShipperName(entity.getShipper().getName());
		}
		else {
			dto.setShipperName("");
		}
		if(entity.getVoucher() != null)
			dto.setVoucherName(entity.getVoucher().getName());
		else
			dto.setVoucherName("");
		List<OrderItemDTO> orderItems = new ArrayList<>();
		List<OrderItemEntity> orderItemEntities = entity.getOrderItems();
		orderItemEntities.forEach(item -> {
			orderItems.add(orderItemConverter.toDTO(item));
		});
		dto.setOrderItems(orderItems);
		return dto;
	}
	

	public OrderEntity toEntity(OrderDTO dto, OrderEntity entity) {
		OrderStatus status = OrderStatus.PENDING;
		if(dto.getStatus() == 1) status = OrderStatus.PREPARING;
		else if(dto.getStatus() == 2) status = OrderStatus.DELIVERY;
		else if(dto.getStatus() == 3) status = OrderStatus.SUCCESS;
		else if(dto.getStatus() == 4) status = OrderStatus.CANCEL;
		entity.setStatus(status);
		if(dto.getShipperId() != null) {
			UserEntity shipper = userRepository.findById(dto.getShipperId()).get();
			entity.setShipper(shipper);
		}
		return entity;
	}
}
