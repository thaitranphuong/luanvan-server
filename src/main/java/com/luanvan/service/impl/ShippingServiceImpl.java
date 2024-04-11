package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.ShippingConverter;
import com.luanvan.dto.ShippingDTO;
import com.luanvan.entity.ShippingEntity;
import com.luanvan.repository.ShippingRepository;
import com.luanvan.service.ShippingService;

@Service
public class ShippingServiceImpl implements ShippingService{
	@Autowired
	private ShippingRepository shippingRepo;

	@Override
	public void save(ShippingDTO dto) {
		shippingRepo.save(ShippingConverter.toEntity(dto));
	}

	@Override
	public List<ShippingDTO> findAll(int page, int limit) {
		List<ShippingEntity> entities = shippingRepo.findAll(page, limit);
		List<ShippingDTO> DTOs = new ArrayList<>();
		for(ShippingEntity item: entities) {
			ShippingDTO dto = ShippingConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<ShippingDTO> findAll(int page, int limit, String name) {
		List<ShippingEntity> entities = shippingRepo.findAll(name, page, limit);
		List<ShippingDTO> DTOs = new ArrayList<>();
		for(ShippingEntity item: entities) {
			ShippingDTO dto = ShippingConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<ShippingEntity> result = shippingRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<ShippingEntity> result = shippingRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		ShippingEntity entity = shippingRepo.findById(id).orElse(null);
		if(entity != null) {
			entity.setRemoved(true);
			shippingRepo.save(entity);
		}
	}

	@Override
	public ShippingDTO findById(Long id) {
		ShippingEntity ShippingEntity = shippingRepo.findById(id).orElse(null);
		ShippingDTO dto;
		if(ShippingEntity != null) {
			dto = ShippingConverter.toDTO(ShippingEntity);
		} else {
			dto = new ShippingDTO();
		}
		return dto;
	}

	@Override
	public List<ShippingDTO> findAll() {
		List<ShippingEntity> entities = shippingRepo.findAll();
		List<ShippingDTO> DTOs = new ArrayList<>();
		for (ShippingEntity item : entities) {
			ShippingDTO dto = ShippingConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(ShippingDTO dto) {
		ShippingEntity entity = shippingRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = ShippingConverter.toEntity(dto, entity);
			shippingRepo.save(entity);
		}
	}
}
