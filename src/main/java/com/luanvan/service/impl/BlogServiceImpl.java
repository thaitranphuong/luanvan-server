package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.converter.BlogConverter;
import com.luanvan.dto.BlogDTO;
import com.luanvan.entity.BlogEntity;
import com.luanvan.repository.BlogRepository;
import com.luanvan.service.BlogService;
import com.luanvan.service.FileService;

@Service
public class BlogServiceImpl implements BlogService{
	@Autowired
	private BlogRepository blogRepo;
	
	@Autowired
	private FileService fileService;

	@Autowired
	private BlogConverter blogConverter;

	@Override
	public boolean save(BlogDTO dto, MultipartFile image) {
		if(fileService.save("banners", image))
			if(blogRepo.save(blogConverter.toEntity(dto)) != null)
				return true;
		return false;
	}

	@Override
	public List<BlogDTO> findAll(int page, int limit) {
		List<BlogEntity> entities = blogRepo.findAll(page, limit);
		List<BlogDTO> DTOs = new ArrayList<>();
		for(BlogEntity item: entities) {
			BlogDTO dto = blogConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<BlogDTO> findAll(int page, int limit, String name) {
		List<BlogEntity> entities = blogRepo.findAll(name, page, limit);
		List<BlogDTO> DTOs = new ArrayList<>();
		for(BlogEntity item: entities) {
			BlogDTO dto = blogConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}
	
	@Override
	public List<BlogDTO> findAll(int page, int limit, Long topicId) {
		List<BlogEntity> entities = blogRepo.findAll(page, limit, topicId);
		List<BlogDTO> DTOs = new ArrayList<>();
		for(BlogEntity item: entities) {
			BlogDTO dto = blogConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<BlogDTO> findAll(int page, int limit, String name, Long topicId) {
		List<BlogEntity> entities = blogRepo.findAll(name, page, limit, topicId);
		List<BlogDTO> DTOs = new ArrayList<>();
		for(BlogEntity item: entities) {
			BlogDTO dto = blogConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<BlogEntity> result = blogRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<BlogEntity> result = blogRepo.findAll(name);
		return result.size();
	}
	
	@Override
	public int totalItem(Long topicId) {
		List<BlogEntity> result = blogRepo.findAll(topicId);
		return result.size();
	}

	@Override
	public int totalItem(String name, Long topicId) {
		List<BlogEntity> result = blogRepo.findAll(name, topicId);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		blogRepo.deleteById(id);
	}

	@Override
	public BlogDTO findById(Long id) {
		BlogEntity BlogEntity = blogRepo.findById(id).orElse(null);
		BlogDTO dto;
		if(BlogEntity != null) {
			dto = blogConverter.toDTO(BlogEntity);
		} else {
			dto = new BlogDTO();
		}
		return dto;
	}

	@Override
	public List<BlogDTO> findAll() {
		List<BlogEntity> entities = blogRepo.findAll();
		List<BlogDTO> DTOs = new ArrayList<>();
		for (BlogEntity item : entities) {
			BlogDTO dto = blogConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(BlogDTO dto) {
		BlogEntity entity = blogRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = blogConverter.toEntity(dto, entity);
			blogRepo.save(entity);
		}
	}
}
