package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.converter.BannerConverter;
import com.luanvan.dto.BannerDTO;
import com.luanvan.entity.BannerEntity;
import com.luanvan.repository.BannerRepository;
import com.luanvan.service.BannerService;
import com.luanvan.service.FileService;

@Service
public class BannerServiceImpl implements BannerService{
	@Autowired
	private BannerRepository bannerRepo;
	
	@Autowired
	private FileService fileService;

	@Override
	public boolean save(BannerDTO dto, MultipartFile image) {
		if(fileService.save("banners", image))
			if(bannerRepo.save(BannerConverter.toEntity(dto)) != null)
				return true;
		return false;
	}

	@Override
	public List<BannerDTO> findAll(int page, int limit) {
		List<BannerEntity> entities = bannerRepo.findAll(page, limit);
		List<BannerDTO> DTOs = new ArrayList<>();
		for(BannerEntity item: entities) {
			BannerDTO dto = BannerConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<BannerDTO> findAll(int page, int limit, String name) {
		List<BannerEntity> entities = bannerRepo.findAll(name, page, limit);
		List<BannerDTO> DTOs = new ArrayList<>();
		for(BannerEntity item: entities) {
			BannerDTO dto = BannerConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<BannerEntity> result = bannerRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<BannerEntity> result = bannerRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		bannerRepo.deleteById(id);
	}

	@Override
	public BannerDTO findById(Long id) {
		BannerEntity BannerEntity = bannerRepo.findById(id).orElse(null);
		BannerDTO dto;
		if(BannerEntity != null) {
			dto = BannerConverter.toDTO(BannerEntity);
		} else {
			dto = new BannerDTO();
		}
		return dto;
	}

	@Override
	public List<BannerDTO> findAll() {
		List<BannerEntity> entities = bannerRepo.findAll();
		List<BannerDTO> DTOs = new ArrayList<>();
		for (BannerEntity item : entities) {
			BannerDTO dto = BannerConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(BannerDTO dto) {
		BannerEntity entity = bannerRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = BannerConverter.toEntity(dto, entity);
			bannerRepo.save(entity);
		}
	}
}
