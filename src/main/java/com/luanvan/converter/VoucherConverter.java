package com.luanvan.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.luanvan.dto.VoucherDTO;
import com.luanvan.entity.VoucherEntity;
import com.luanvan.enums.VoucherCategory;

public class VoucherConverter {
	public static VoucherEntity toEntity(VoucherDTO dto) {
		VoucherEntity entity = new VoucherEntity();
		entity.setIndex(dto.get_index());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate, endDate;
		try {
			startDate = formatter.parse(dto.getStartTime());
			endDate = formatter.parse(dto.getEndTime());
		} catch (ParseException e) {
			startDate = new Date(0);
			endDate = new Date(0);
		}
		entity.setStartTime(startDate);
		entity.setEndTime(endDate);
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		entity.setRemainingQuantity(dto.getQuantity());
		entity.setMaxDiscount(dto.getMaxDiscount());
		if(dto.isCategory()) entity.setCategory(VoucherCategory.VND);
		else entity.setCategory(VoucherCategory.PERCENTAGE);
		return entity;
	}
	
	public static VoucherDTO toDTO(VoucherEntity entity) {
		VoucherDTO dto = new VoucherDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.set_index(entity.getIndex());
		dto.setStartTime(entity.getStartTime());
		dto.setEndTime(entity.getEndTime());
		dto.setQuantity(entity.getQuantity());
		dto.setMaxDiscount(entity.getMaxDiscount());
		dto.setRemainingQuantity(entity.getRemainingQuantity());
		if(entity.getCategory() == VoucherCategory.VND) dto.setCategory(true);
		else dto.setCategory(false);
		return dto;
	}
	

	public static VoucherEntity toEntity(VoucherDTO dto, VoucherEntity entity) {
		entity.setIndex(dto.get_index());
		if(dto.isCategory()) entity.setCategory(VoucherCategory.VND);
		else entity.setCategory(VoucherCategory.PERCENTAGE);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate, endDate;
		try {
			startDate = formatter.parse(dto.getStartTime());
			endDate = formatter.parse(dto.getEndTime());
		} catch (ParseException e) {
			startDate = new Date(0);
			endDate = new Date(0);
		}
		entity.setStartTime(startDate);
		entity.setEndTime(endDate);
		entity.setName(dto.getName());
		entity.setQuantity(dto.getQuantity());
		int remainQuantity = dto.getQuantity() - entity.getQuantity() + entity.getRemainingQuantity();
		if (remainQuantity >= 0) entity.setRemainingQuantity(remainQuantity);
		else entity.setRemainingQuantity(0);
		entity.setMaxDiscount(dto.getMaxDiscount());
		return entity;
	}
}
