package com.luanvan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.entity.UserEntity;
import com.luanvan.security.UserPrincipal;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class HelloController {
	
	@GetMapping("/")
	public String greeting() {
		return "Hello, World!";
	}
	
	@GetMapping("/secured")
	public ResponseEntity<?> secured() {
		
		return ResponseEntity.ok().body("123");
	}
	
	@PostMapping("/secured")
	public String securedPost() {
		
		return "234";
	}
	
	@GetMapping("/admin")
	public String admin(@AuthenticationPrincipal UserPrincipal principal) {
		return "If you see this, then you are login as ADMIN " + principal.getEmail()
				+ " UserId " + principal.getUserId();
	}
}
