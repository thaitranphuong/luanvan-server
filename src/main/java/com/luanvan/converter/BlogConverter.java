package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.BlogDTO;
import com.luanvan.entity.BlogEntity;
import com.luanvan.repository.TopicRepository;
import com.luanvan.repository.UserRepository;

@Component
public class BlogConverter {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TopicRepository topicRepository;
	
	public BlogEntity toEntity(BlogDTO dto) {
		BlogEntity entity = new BlogEntity();
		entity.setCode(dto.getCode());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		entity.setTitle(dto.getTitle());
		entity.setAuthor(userRepository.findById(dto.getAuthorId()).get());
		entity.setTopic(topicRepository.findById(dto.getTopicId()).get());
		return entity;
	}
	
	public BlogDTO toDTO(BlogEntity entity) {
		BlogDTO dto = new BlogDTO();
		dto.setId(entity.getId());
		dto.setCode(entity.getCode());
		dto.setContent(entity.getContent());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setShortDescription(entity.getShortDescription());
		dto.setThumbnail(entity.getThumbnail());
		dto.setTitle(entity.getTitle());
		dto.setView(entity.getView());
		dto.setAuthorId(entity.getAuthor().getId());
		dto.setAuthorName(entity.getAuthor().getName());
		dto.setTopicId(entity.getTopic().getId());
		dto.setTopicName(entity.getTopic().getName());
		dto.setCommentQuantity(entity.getComments().size());
		return dto;
	}
	

	public BlogEntity toEntity(BlogDTO dto, BlogEntity entity) {
		entity.setCode(dto.getCode());
		entity.setContent(dto.getContent());
		entity.setShortDescription(dto.getShortDescription());
		entity.setThumbnail(dto.getThumbnail());
		entity.setTitle(dto.getTitle());
		entity.setTopic(topicRepository.findById(dto.getTopicId()).get());
		return entity;
	}
}
