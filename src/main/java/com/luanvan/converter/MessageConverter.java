package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.MessageDTO;
import com.luanvan.entity.MessageEntity;
import com.luanvan.repository.UserRepository;
import com.luanvan.entity.UserEntity;

@Component
public class MessageConverter {
	@Autowired
	private UserRepository userRepository;
	
	public MessageEntity toEntity(MessageDTO dto) {
		MessageEntity entity = new MessageEntity();
		entity.setContent(dto.getContent());
		entity.setCreatedTime(dto.getCreatedTime());
		entity.setRead(dto.isRead());
		UserEntity sender = userRepository.findById(dto.getSenderId()).orElse(null);
		entity.setSender(sender);
		UserEntity receiver = userRepository.findById(dto.getReceiverId()).orElse(null);
		entity.setReceiver(receiver);
		return entity;
	}
	
	public static MessageDTO toDTO(MessageEntity entity) {
		MessageDTO dto = new MessageDTO();
		dto.setId(entity.getId());
		dto.setContent(entity.getContent());
		dto.setRead(entity.isRead());
		dto.setCreatedTime(entity.getCreatedTime());
        dto.setSenderId(entity.getSender().getId());
        dto.setReceiverId(entity.getReceiver().getId());
        dto.setSenderName(entity.getSender().getName());
        dto.setAvatar(entity.getSender().getAvatar());
		return dto;
	}
	

	public MessageEntity toEntity(MessageDTO dto, MessageEntity entity) {
		entity.setRead(dto.isRead());
		return entity;
	}
}
