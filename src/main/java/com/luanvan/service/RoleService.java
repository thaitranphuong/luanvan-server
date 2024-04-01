package com.luanvan.service;

import com.luanvan.entity.RoleEntity;

public interface RoleService {
	RoleEntity findByName(String name);
	void save(RoleEntity role);
}
