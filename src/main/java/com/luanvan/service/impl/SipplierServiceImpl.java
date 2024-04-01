package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.SupplierConverter;
import com.luanvan.dto.SupplierDTO;
import com.luanvan.entity.SupplierEntity;
import com.luanvan.repository.SupplierRepository;
import com.luanvan.service.SupplierService;

@Service
public class SipplierServiceImpl implements SupplierService{
	@Autowired
	private SupplierRepository supplierRepo;

	@Override
	public void save(SupplierDTO dto) {
		supplierRepo.save(SupplierConverter.toEntity(dto));
	}

	@Override
	public List<SupplierDTO> findAll(int page, int limit) {
		List<SupplierEntity> entities = supplierRepo.findAll(page, limit);
		List<SupplierDTO> DTOs = new ArrayList<>();
		for(SupplierEntity item: entities) {
			SupplierDTO dto = SupplierConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<SupplierDTO> findAll(int page, int limit, String name) {
		List<SupplierEntity> entities = supplierRepo.findAll(name, page, limit);
		List<SupplierDTO> DTOs = new ArrayList<>();
		for(SupplierEntity item: entities) {
			SupplierDTO dto = SupplierConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<SupplierEntity> result = supplierRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<SupplierEntity> result = supplierRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		supplierRepo.deleteById(id);
	}

	@Override
	public SupplierDTO findById(Long id) {
		SupplierEntity SupplierEntity = supplierRepo.findById(id).orElse(null);
		SupplierDTO dto;
		if(SupplierEntity != null) {
			dto = SupplierConverter.toDTO(SupplierEntity);
		} else {
			dto = new SupplierDTO();
		}
		return dto;
	}

	@Override
	public List<SupplierDTO> findAll() {
		List<SupplierEntity> entities = supplierRepo.findAll();
		List<SupplierDTO> DTOs = new ArrayList<>();
		for (SupplierEntity item : entities) {
			SupplierDTO dto = SupplierConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(SupplierDTO dto) {
		SupplierEntity entity = supplierRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = SupplierConverter.toEntity(dto, entity);
			supplierRepo.save(entity);
		}
	}
}
