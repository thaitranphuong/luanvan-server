package com.luanvan.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.OrderDTO;
import com.luanvan.dto.pagination.OrderOutput;
import com.luanvan.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody OrderDTO dto) {
		Long id = orderService.save(dto);
		if (id != null)
			return ResponseEntity.ok(id);
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping
	public OrderOutput getOrders(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "status", defaultValue = "none") String strStatus) {
		OrderOutput result = new OrderOutput();
		result.setPage(page);
		if (!strStatus.equals("none")) {
			result.setListResult(orderService.findAll((page - 1) * limit, limit, strStatus));
			result.setTotalPage((int) Math.ceil((double) orderService.totalItem(strStatus) / limit));
		} else {
			result.setListResult(orderService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) orderService.totalItem() / limit));
		}
		return result;
	}
	
	@GetMapping("/get-by-user/{userId}")
	public ResponseEntity<?> getOrdersByUserId(@PathVariable Long userId,
					@RequestParam(name = "status", defaultValue = "none") String strStatus) {
		List<OrderDTO> result = new ArrayList<>();
		if (!strStatus.equals("none")) {
			result = orderService.findAll(userId, strStatus);
		} else {
			result = orderService.findAll(userId);
		}
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{id}")
	public OrderDTO getOrder(@PathVariable Long id) {
		return orderService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<OrderDTO> getAllOrders() {
		return orderService.findAll();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody OrderDTO dto) {
		if(orderService.update(dto))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
