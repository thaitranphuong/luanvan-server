package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.UserDTO;

public interface UserService {
	UserDTO findByEmail(String email);
	UserDTO save(UserDTO user);
	UserDTO save(UserDTO user, String roleName);
	List<UserDTO> findAllByRoleNames(String[] roleNames);
	void update(UserDTO user);
	List<UserDTO> findAll();
	List<UserDTO> findAll(int page, int limit);
	List<UserDTO> findAll(int page, int limit, String name);
	int totalItem();
	int totalItem(String name);
	void delete(Long id);
	UserDTO findById(Long id);
}
