package com.luanvan.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.luanvan.dto.UserDTO;
import com.luanvan.entity.UserEntity;

public class UserConverter {
	
	static PasswordEncoder pwdEncoder = new BCryptPasswordEncoder();
	
	public static UserEntity toEntity(UserDTO userDTO) {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(userDTO.getName());
		userEntity.setAddress(userDTO.getAddress());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setGender(userDTO.isGender());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setAvatar(userDTO.getAvatar());
		userEntity.setPassword(pwdEncoder.encode(userDTO.getPassword()));
		return userEntity;
	}
	
	public static UserDTO toDTO(UserEntity userEntity) {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(userEntity.getId());
		userDTO.setName(userEntity.getName());
		userDTO.setAddress(userEntity.getAddress());
		userDTO.setBirthday(userEntity.getBirthday());
		userDTO.setEmail(userEntity.getEmail());
		userDTO.setGender(userEntity.isGender());
		userDTO.setPhone(userEntity.getPhone());
		userDTO.setRole(userEntity.getRole().getName());
		userDTO.setAvatar(userEntity.getAvatar());
		return userDTO;
	}
	

	public static UserEntity toEntity(UserDTO userDTO, UserEntity userEntity) {
		userEntity.setName(userDTO.getName());
		userEntity.setAddress(userDTO.getAddress());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(userDTO.getBirthday());
		} catch (ParseException e) {
			date = new Date(0);
		}
		userEntity.setBirthday(date);
		userEntity.setGender(userDTO.isGender());
		userEntity.setPhone(userDTO.getPhone());
		userEntity.setAvatar(userDTO.getAvatar());
		return userEntity;
	}
}
