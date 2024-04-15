package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.AddressDTO;
import com.luanvan.entity.AddressEntity;
import com.luanvan.repository.UserRepository;

@Component
public class AddressConverter {
	@Autowired
	private UserRepository userRepository;
	
	public AddressEntity toEntity(AddressDTO dto) {
		AddressEntity entity = new AddressEntity();
		entity.setUsername(dto.getUsername());
		entity.setCity(dto.getCity());
		entity.setDistrict(dto.getDistrict());
		entity.setWard(dto.getWard());
		entity.setPhone(dto.getPhone());
		entity.setCoordinates(dto.getCoordinates());
		entity.setSpecification(dto.getSpecification());
		entity.setUser(userRepository.findById(dto.getUserId()).get());
		return entity;
	}
	
	public AddressDTO toDTO(AddressEntity entity) {
		AddressDTO dto = new AddressDTO();
		dto.setId(entity.getId());
		dto.setCity(entity.getCity());
		dto.setUsername(entity.getUsername());
		dto.setDistrict(entity.getDistrict());
		dto.setWard(entity.getWard());
		dto.setPhone(entity.getPhone());
		dto.setCoordinates(entity.getCoordinates());
		dto.setSpecification(entity.getSpecification());
		return dto;
	}
	

	public AddressEntity toEntity(AddressDTO dto, AddressEntity entity) {
		entity.setUsername(dto.getUsername());
		entity.setCity(dto.getCity());
		entity.setDistrict(dto.getDistrict());
		entity.setWard(dto.getWard());
		entity.setPhone(dto.getPhone());
		entity.setCoordinates(dto.getCoordinates());
		entity.setSpecification(dto.getSpecification());
		return entity;
	}
}
