package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.UserConverter;
import com.luanvan.dto.UserDTO;
import com.luanvan.entity.RoleEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.repository.RoleRepository;
import com.luanvan.repository.UserRepository;
import com.luanvan.service.RoleService;
import com.luanvan.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	RoleRepository roleRepository;

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

	@Override
	public List<UserDTO> findAllByRoleNames(String[] roleNames) {
		List<UserDTO> DTOs = new ArrayList<>();
		for(int i=0; i<roleNames.length; i++) {
			List<UserEntity> entities = userRepo.findByRoleName(roleNames[i]).orElse(null);
			entities.forEach(entity -> {
				UserDTO dto = UserConverter.toDTO(entity);
				DTOs.add(dto);
			});
		}
		return DTOs;
	}

	@Override
	public UserDTO save(UserDTO user) {
		if (userRepo.findByEmail(user.getEmail()).isEmpty()) {
			RoleEntity role = roleRepository.findById(user.getRoleId()).get();
			UserEntity userEntity = UserConverter.toEntity(user);
			userEntity.setRole(role);
			return UserConverter.toDTO(userRepo.save(userEntity));
		}
		else {
			return new UserDTO();
		}
	}
	
	@Override
	public List<UserDTO> findAll(int page, int limit) {
		List<UserEntity> entities = userRepo.findAll(page, limit);
		List<UserDTO> DTOs = new ArrayList<>();
		for(UserEntity item: entities) {
			UserDTO dto = UserConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public List<UserDTO> findAll(int page, int limit, String name) {
		List<UserEntity> entities = userRepo.findAll(name, page, limit);
		List<UserDTO> DTOs = new ArrayList<>();
		for(UserEntity item: entities) {
			UserDTO dto = UserConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public int totalItem() {
		List<UserEntity> result = userRepo.findAll();
		return result.size();
	}

	@Override
	public int totalItem(String name) {
		List<UserEntity> result = userRepo.findAll(name);
		return result.size();
	}

	@Override
	public void delete(Long id) {
		userRepo.deleteById(id);
	}

	@Override
	public UserDTO findById(Long id) {
		UserEntity userEntity = userRepo.findById(id).orElse(null);
		UserDTO dto;
		if(userEntity != null) {
			dto = UserConverter.toDTO(userEntity);
			dto.setRoleId(userEntity.getRole().getId());
		} else {
			dto = new UserDTO();
		}
		return dto;
	}

	@Override
	public List<UserDTO> findAll() {
		List<UserEntity> entities = userRepo.findAll();
		List<UserDTO> DTOs = new ArrayList<>();
		for (UserEntity item : entities) {
			UserDTO dto = UserConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}

	@Override
	public void update(UserDTO dto) {
		UserEntity entity = userRepo.findById(dto.getId()).orElse(null);
		RoleEntity role = roleRepository.findById(dto.getRoleId()).get();
		if(entity != null) {
			entity = UserConverter.toEntity(dto, entity);
			entity.setRole(role);
			userRepo.save(entity);
		}
	}
}
