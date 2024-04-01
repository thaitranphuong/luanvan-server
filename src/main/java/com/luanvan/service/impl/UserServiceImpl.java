package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.luanvan.converter.UserConverter;
import com.luanvan.dto.UserDTO;
import com.luanvan.entity.RoleEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.repository.UserRepository;
import com.luanvan.service.RoleService;
import com.luanvan.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;

	@Override
	public UserDTO findByEmail(String email) {
		UserEntity userEntity = userRepo.findByEmail(email).orElse(null);
		if(userEntity != null)
			return UserConverter.toDTO(userEntity);
		else
			return null;
	}

	@Override
	public UserDTO save(UserDTO user, String roleName) {
		if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
			RoleEntity role = roleService.findByName(roleName);
			UserEntity userEntity = UserConverter.toEntity(user);
			userEntity.setRole(role);
			return UserConverter.toDTO(userRepo.save(userEntity));
		}
		else {
			return new UserDTO();
		}
	}

}
