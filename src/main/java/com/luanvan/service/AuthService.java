package com.luanvan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.luanvan.converter.UserConverter;
import com.luanvan.dto.LoginResponse;
import com.luanvan.dto.UserDTO;
import com.luanvan.entity.UserEntity;
import com.luanvan.security.JwtIssuer;
import com.luanvan.security.UserPrincipal;

@Service
public class AuthService {
	
	private final JwtIssuer jwtIssuer;
		
	private final AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	public AuthService(JwtIssuer jwtIssuer, AuthenticationManager authenticationManager) {
		this.jwtIssuer = jwtIssuer;
		this.authenticationManager = authenticationManager;
	}
	
	public LoginResponse attemptLogin(String email, String password) {
		var authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(email, password)
				);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		var principal = (UserPrincipal) authentication.getPrincipal();
		
		var roles = principal.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.toList();
		
		var token = jwtIssuer.issue(principal.getUserId(), principal.getEmail(), roles);
		UserDTO userDTO = userService.findByEmail(principal.getEmail());
		LoginResponse loginResponse = new LoginResponse(token, userDTO);
		return loginResponse;
	}

}
