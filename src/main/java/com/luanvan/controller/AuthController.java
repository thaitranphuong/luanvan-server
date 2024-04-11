package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.LoginRequest;
import com.luanvan.dto.LoginResponse;
import com.luanvan.dto.UserDTO;
import com.luanvan.service.AuthService;
import com.luanvan.service.UserService;



@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest request) {
		return authService.attemptLogin(request.getEmail(), request.getPassword());
	}
	
	@PostMapping("/signup")
	public UserDTO signup(@RequestBody UserDTO user) {
		return userService.save(user, "customer");
	}
}
