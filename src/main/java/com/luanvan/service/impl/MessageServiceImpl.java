package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.MessageConverter;
import com.luanvan.dto.MessageDTO;
import com.luanvan.entity.MessageEntity;
import com.luanvan.repository.MessageRepository;
import com.luanvan.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService{
	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MessageConverter messageConverter;

	@Override
	public List<MessageDTO> findAll(Long userIdFirst, Long userIdSecond) {
		List<MessageEntity> entities = messageRepository.findAll(userIdFirst, userIdSecond).orElse(null);
		List<MessageDTO> DTOs = new ArrayList<>();
		entities.forEach(entity -> {
			MessageDTO dto = MessageConverter.toDTO(entity);
			DTOs.add(dto);
		});
		return DTOs;
	}

	@Override
	public void save(MessageDTO message) {
		messageRepository.save(messageConverter.toEntity(message));
	}

}
