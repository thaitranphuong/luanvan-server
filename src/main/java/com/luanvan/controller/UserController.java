package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.UserDTO;
import com.luanvan.dto.pagination.UserOutput;
import com.luanvan.service.UserService;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/get-all-customer")
	public List<UserDTO> getAllUser() {
		return userService.findAllByRoleNames(new String[] {"customer"});	
	}
	
	@GetMapping(value = "/get-all-admin")
	public List<UserDTO> getAllAdmin() {
		return userService.findAllByRoleNames(new String[] {"admin", "saler"});	
	}
	
	@PostMapping
	public UserDTO save(@RequestBody UserDTO user) {
		return userService.save(user);
	}
	
	@GetMapping
	public UserOutput getUsers(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		UserOutput result = new UserOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(userService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) userService.totalItem(name) / limit));
		} else {
			result.setListResult(userService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) userService.totalItem() / limit));
		}
		return result;
	}
	
	@GetMapping("/{id}")
	public UserDTO getUser(@PathVariable Long id) {
		return userService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public UserDTO update(@RequestBody UserDTO dto) {
		userService.update(dto);
		return new UserDTO();
	}
}
