package com.luanvan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.entity.RoleEntity;
import com.luanvan.repository.RoleRepository;
import com.luanvan.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	private RoleRepository roleRepo;

	@Override
	public RoleEntity findByName(String name) {
		return roleRepo.findByName(name).orElse(null);
	}

	@Override
	public void save(RoleEntity role) {
		roleRepo.save(role);
	}

}
