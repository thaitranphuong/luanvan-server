package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.MessageDTO;
import com.luanvan.service.MessageService;

@RestController
@CrossOrigin
@RequestMapping("/message")
public class MessageController {
	@Autowired
	private MessageService messageService;
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
	
	@GetMapping(value = "/get-all/{userIdFirst}/{userIdSecond}")
	public List<MessageDTO> getAllMessage(@PathVariable(name = "userIdFirst") Long userIdFirst,
									@PathVariable(name = "userIdSecond") Long userIdSecond) {
		return messageService.findAll(userIdFirst, userIdSecond);
	}

    @MessageMapping("/private-message")
    public void privateReceiveMessage(@Payload MessageDTO messageDTO){
        simpMessagingTemplate.convertAndSendToUser(messageDTO.getReceiverId().toString(), "/private", messageDTO);
        messageService.save(messageDTO);
    }
}
