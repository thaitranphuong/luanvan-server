package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.AddressConverter;
import com.luanvan.dto.AddressDTO;
import com.luanvan.entity.AddressEntity;
import com.luanvan.repository.AddressRepository;
import com.luanvan.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private AddressConverter addressConverter;

	@Override
	public boolean save(AddressDTO address) {
		return addressRepository.save(addressConverter.toEntity(address)) != null;
	}

	@Override
	public boolean update(AddressDTO address) {
		return addressRepository.save(addressConverter.toEntity(
				address, addressRepository.findById(address.getId()).get())) != null;
	}

	@Override
	public List<AddressDTO> findAll(Long userId) {
		List<AddressDTO> DTOs = new ArrayList<>();
		List<AddressEntity> entities = addressRepository.findAll(userId);
		entities.forEach(item -> {
			DTOs.add(addressConverter.toDTO(item));
		});
		return DTOs;
	}

	@Override
	public boolean delete(Long id) {
		AddressEntity entity = addressRepository.findById(id).get();
		entity.setRemoved(true);
		return addressRepository.save(entity) != null;
	}

	@Override
	public AddressDTO findById(Long id) {
		return addressConverter.toDTO(addressRepository.findById(id).get());
	}

}
