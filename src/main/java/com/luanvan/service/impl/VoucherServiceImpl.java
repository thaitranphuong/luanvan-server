package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.VoucherConverter;
import com.luanvan.dto.VoucherDTO;
import com.luanvan.entity.UserEntity;
import com.luanvan.entity.VoucherEntity;
import com.luanvan.repository.UserRepository;
import com.luanvan.repository.VoucherRepository;
import com.luanvan.service.VoucherService;

@Service
public class VoucherServiceImpl implements VoucherService{
	@Autowired
	private VoucherRepository voucherRepo;
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public boolean save(VoucherDTO dto) {
		if(voucherRepo.findByName(dto.getName()) == null) {
			voucherRepo.save(VoucherConverter.toEntity(dto));
			return true;
		}
		else return false;
	}
	
	//Voucher da luu
	@Override
	public List<VoucherDTO> findAll(Long userId) {
		List<VoucherEntity> entities = voucherRepo.findAllByUserId(userId);
		List<VoucherDTO> DTOs = new ArrayList<>();
		for(VoucherEntity item: entities) {
			VoucherDTO dto = VoucherConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}
	
	//Voucher chua luu
	@Override
	public List<VoucherDTO> findAll(int page, int limit, Long userId) {
		List<VoucherEntity> entities = voucherRepo.findAll(userId, page, limit);
		List<VoucherDTO> DTOs = new ArrayList<>();
		for(VoucherEntity item: entities) {
			VoucherDTO dto = VoucherConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	//Trang admin
	@Override
	public List<VoucherDTO> findAll(int page, int limit) {
		List<VoucherEntity> entities = voucherRepo.findAll(page, limit);
		List<VoucherDTO> DTOs = new ArrayList<>();
		for(VoucherEntity item: entities) {
			VoucherDTO dto = VoucherConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	//Trang admin
	@Override
	public List<VoucherDTO> findAll(int page, int limit, String name) {
		List<VoucherEntity> entities = voucherRepo.findAll(name, page, limit);
		List<VoucherDTO> DTOs = new ArrayList<>();
		for(VoucherEntity item: entities) {
			VoucherDTO dto = VoucherConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<VoucherEntity> result = voucherRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<VoucherEntity> result = voucherRepo.findAll(name);
		return result.size();
	}

	//Tat ca voucher chua luu
	@Override
	public int totalItem(Long userId) {
		List<VoucherEntity> result = voucherRepo.findAll(userId);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		VoucherEntity entity = voucherRepo.findById(id).orElse(null);
		if(entity != null) {
			entity.setRemoved(true);
			voucherRepo.save(entity);
		}
	}

	@Override
	public VoucherDTO findById(Long id) {
		VoucherEntity VoucherEntity = voucherRepo.findById(id).orElse(null);
		VoucherDTO dto;
		if(VoucherEntity != null) {
			dto = VoucherConverter.toDTO(VoucherEntity);
		} else {
			dto = new VoucherDTO();
		}
		return dto;
	}

	@Override
	public List<VoucherDTO> findAll() {
		List<VoucherEntity> entities = voucherRepo.findAll();
		List<VoucherDTO> DTOs = new ArrayList<>();
		for (VoucherEntity item : entities) {
			VoucherDTO dto = VoucherConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(VoucherDTO dto) {
		VoucherEntity entity = voucherRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = VoucherConverter.toEntity(dto, entity);
			voucherRepo.save(entity);
		}
	}

	@Override
	public boolean collect(Long userId, Long voucherId) {
		VoucherEntity voucher = voucherRepo.findById(voucherId).get();
		UserEntity user = userRepo.findById(userId).get();
		voucher.getUsers().add(user);
		user.getVouchers().add(voucher);
		if(voucherRepo.save(voucher) != null)
			return true;
		return false;
	}

	

	

}
