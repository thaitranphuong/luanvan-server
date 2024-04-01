package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.BrandConverter;
import com.luanvan.dto.BrandDTO;
import com.luanvan.entity.BrandEntity;
import com.luanvan.repository.BrandRepository;
import com.luanvan.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{

	@Autowired
	private BrandRepository brandRepo;

	@Override
	public void save(BrandDTO brand) {
		brandRepo.save(BrandConverter.toEntity(brand));
	}

	@Override
	public List<BrandDTO> findAll(int page, int limit) {
		List<BrandEntity> brandEntities = brandRepo.findAll(page, limit);
		List<BrandDTO> brandDTOs = new ArrayList<>();
		for(BrandEntity item: brandEntities) {
			BrandDTO brandDTO = BrandConverter.toDTO(item);
			brandDTOs.add(brandDTO);
		}
		return brandDTOs;
	}

	@Override
	public List<BrandDTO> findAll(int page, int limit, String name) {
		List<BrandEntity> brandEntities = brandRepo.findAll(name, page, limit);
		List<BrandDTO> brandDTOs = new ArrayList<>();
		for(BrandEntity item: brandEntities) {
			BrandDTO brandDTO = BrandConverter.toDTO(item);
			brandDTOs.add(brandDTO);
		}
		return brandDTOs;
	}

	@Override
	public int totalItem() {
		List<BrandEntity> result = brandRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<BrandEntity> result = brandRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		brandRepo.deleteById(id);
	}

	@Override
	public BrandDTO findById(Long id) {
		BrandEntity brandEntity = brandRepo.findById(id).orElse(null);
		BrandDTO brandDTO;
		if(brandEntity != null) {
			brandDTO = BrandConverter.toDTO(brandEntity);
		} else {
			brandDTO = new BrandDTO();
		}
		return brandDTO;
	}

	@Override
	public List<BrandDTO> findAll() {
		List<BrandEntity> brandEntities = brandRepo.findAll();
		List<BrandDTO> brandDTOs = new ArrayList<>();
		for (BrandEntity item : brandEntities) {
			BrandDTO dto = BrandConverter.toDTO(item);
			brandDTOs.add(dto);
		}
		return brandDTOs;
	}

	@Override
	public void update(BrandDTO brand) {
		BrandEntity brandEntity = brandRepo.findById(brand.getId()).orElse(null);
		if(brandEntity != null) {
			brandEntity = BrandConverter.toEntity(brand, brandEntity);
			brandRepo.save(brandEntity);
		}
	}

}
