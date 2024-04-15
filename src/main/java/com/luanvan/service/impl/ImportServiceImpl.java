package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.ImportConverter;
import com.luanvan.converter.ImportDetailConverter;
import com.luanvan.dto.ImportDTO;
import com.luanvan.dto.ImportDetailDTO;
import com.luanvan.entity.ImportEntity;
import com.luanvan.entity.ProductDetailSizeEntity;
import com.luanvan.repository.ImportDetailRepository;
import com.luanvan.repository.ImportRepository;
import com.luanvan.repository.ProductDetailSizeRepository;
import com.luanvan.service.ImportService;

@Service
public class ImportServiceImpl implements ImportService{
	@Autowired
	private ImportRepository importRepo;
	
	@Autowired
	private ImportDetailRepository importDetailRepo;

	@Autowired
	private ImportConverter importConverter;
	
	@Autowired
	private ImportDetailConverter importDetailConverter;
	
	@Autowired
	private ProductDetailSizeRepository productDetailSizeRepository;

	@Override
	public Long save(ImportDTO dto) {
		Long importId = importRepo.save(importConverter.toEntity(dto)).getId();
		if(importId != null) {
			dto.getImportDetails().forEach(item -> {
				item.setImportId(importId);
				importDetailRepo.save(importDetailConverter.toEntity(item));
				ProductDetailSizeEntity productDetailSizeEntity = 
						productDetailSizeRepository.findById(item.getProductDetailSizeId()).get();
				productDetailSizeEntity.setQuantity(productDetailSizeEntity.getQuantity() + item.getQuantity());
				productDetailSizeRepository.save(productDetailSizeEntity);
			});
		}
		return importId;
	}

	@Override
	public List<ImportDTO> findAll(int page, int limit) {
		List<ImportEntity> entities = importRepo.findAll(page, limit);
		List<ImportDTO> DTOs = new ArrayList<>();
		for(ImportEntity item: entities) {
			ImportDTO dto = importConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<ImportDTO> findAll(int page, int limit, Long id) {
		List<ImportEntity> entities = importRepo.findAll(id, page, limit);
		List<ImportDTO> DTOs = new ArrayList<>();
		for(ImportEntity item: entities) {
			ImportDTO dto = importConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<ImportEntity> result = importRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(Long id) {
		List<ImportEntity> result = importRepo.findAll(id);
		return result.size();
	}

	@Override
	public ImportDTO findById(Long id) {
		ImportEntity importEntity = importRepo.findById(id).orElse(null);
		ImportDTO dto;
		if(importEntity != null) {
			dto = importConverter.toDTO(importEntity);
			List<ImportDetailDTO> importDetailDTOs = new ArrayList<>();
			importEntity.getImportDetails().forEach(item -> {
				importDetailDTOs.add(importDetailConverter.toDTO(item));
			});
			dto.setImportDetails(importDetailDTOs);
		} else {
			dto = new ImportDTO();
		}
		return dto;
	}

	@Override
	public List<ImportDTO> findAll() {
		List<ImportEntity> entities = importRepo.findAll();
		List<ImportDTO> DTOs = new ArrayList<>();
		for (ImportEntity item : entities) {
			ImportDTO dto = importConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}
}
