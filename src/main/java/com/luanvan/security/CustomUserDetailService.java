package com.luanvan.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.luanvan.repository.UserRepository;
import com.luanvan.service.UserService;

@Component
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		var user = userRepository.findByEmail(email).get();
		var userPrincipal = new UserPrincipal(user.getId(), user.getEmail(),
				List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().getName().toUpperCase())), user.getPassword());
		return userPrincipal;
	}
	
}
