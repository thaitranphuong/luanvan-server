package com.luanvan.converter;

import com.luanvan.dto.TopicDTO;
import com.luanvan.entity.TopicEntity;

public class TopicConverter {
	public static TopicEntity toEntity(TopicDTO dto) {
		TopicEntity entity = new TopicEntity();
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		return entity;
	}
	
	public static TopicDTO toDTO(TopicEntity entity) {
		TopicDTO dto = new TopicDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCode(entity.getCode());
		return dto;
	}
	

	public static TopicEntity toEntity(TopicDTO dto, TopicEntity entity) {
		entity.setName(dto.getName());
		entity.setCode(dto.getCode());
		return entity;
	}
}
