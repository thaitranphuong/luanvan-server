package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.TopicConverter;
import com.luanvan.dto.TopicDTO;
import com.luanvan.entity.TopicEntity;
import com.luanvan.repository.TopicRepository;
import com.luanvan.service.TopicService;

@Service
public class TopicServiceImpl implements TopicService{
	@Autowired
	private TopicRepository topicRepo;

	@Override
	public void save(TopicDTO dto) {
		topicRepo.save(TopicConverter.toEntity(dto));
	}

	@Override
	public List<TopicDTO> findAll(int page, int limit) {
		List<TopicEntity> entities = topicRepo.findAll(page, limit);
		List<TopicDTO> DTOs = new ArrayList<>();
		for(TopicEntity item: entities) {
			TopicDTO dto = TopicConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<TopicDTO> findAll(int page, int limit, String name) {
		List<TopicEntity> entities = topicRepo.findAll(name, page, limit);
		List<TopicDTO> DTOs = new ArrayList<>();
		for(TopicEntity item: entities) {
			TopicDTO dto = TopicConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<TopicEntity> result = topicRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<TopicEntity> result = topicRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		topicRepo.deleteById(id);
	}

	@Override
	public TopicDTO findById(Long id) {
		TopicEntity TopicEntity = topicRepo.findById(id).orElse(null);
		TopicDTO dto;
		if(TopicEntity != null) {
			dto = TopicConverter.toDTO(TopicEntity);
		} else {
			dto = new TopicDTO();
		}
		return dto;
	}

	@Override
	public List<TopicDTO> findAll() {
		List<TopicEntity> entities = topicRepo.findAll();
		List<TopicDTO> DTOs = new ArrayList<>();
		for (TopicEntity item : entities) {
			TopicDTO dto = TopicConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(TopicDTO dto) {
		TopicEntity entity = topicRepo.findById(dto.getId()).orElse(null);
		if(entity != null) {
			entity = TopicConverter.toEntity(dto, entity);
			topicRepo.save(entity);
		}
	}
}
