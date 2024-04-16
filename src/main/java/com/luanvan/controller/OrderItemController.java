package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.OrderItemDTO;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.service.OrderItemService;

@RestController
@CrossOrigin
@RequestMapping("/order-item")
public class OrderItemController {
	@Autowired
	private OrderItemService orderItemService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody OrderItemDTO dto) {
		if (orderItemService.save(dto))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
