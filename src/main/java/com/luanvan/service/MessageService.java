package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.MessageDTO;

public interface MessageService {
	List<MessageDTO> findAll(Long userIdFirst, Long userIdSecond);
	void save (MessageDTO message);
}
