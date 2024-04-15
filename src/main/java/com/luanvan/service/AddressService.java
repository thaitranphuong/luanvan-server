package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.AddressDTO;

public interface AddressService {
	boolean save(AddressDTO address);
	boolean update(AddressDTO address);
	List<AddressDTO> findAll(Long userId);
	boolean delete(Long id);
	AddressDTO findById(Long id);
}
