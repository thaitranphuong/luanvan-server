package com.luanvan.service;

import com.luanvan.dto.UserDTO;

public interface UserService {
	UserDTO findByEmail(String email);
	UserDTO save(UserDTO user, String roleName);
}
