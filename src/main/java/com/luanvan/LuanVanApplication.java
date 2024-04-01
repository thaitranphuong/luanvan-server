package com.luanvan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.luanvan.dto.UserDTO;
import com.luanvan.entity.RoleEntity;
import com.luanvan.service.RoleService;
import com.luanvan.service.UserService;

@SpringBootApplication
public class LuanVanApplication implements ApplicationRunner {
	
	@Autowired
    private RoleService roleService;
	
	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(LuanVanApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		createRole();
		createAdmin();
	}
	
	public void createRole() {
		RoleEntity adminRole = roleService.findByName("admin");
		RoleEntity salerRole = roleService.findByName("saler");
		RoleEntity shipperRole = roleService.findByName("shipper");
		RoleEntity customerRole = roleService.findByName("customer");
		if(adminRole == null) {
			adminRole = new RoleEntity();
			adminRole.setName("admin");
			roleService.save(adminRole);
		}
		
		if(salerRole == null) {
			salerRole = new RoleEntity();
			salerRole.setName("saler");
			roleService.save(salerRole);
		}
		
		if(shipperRole == null) {
			shipperRole = new RoleEntity();
			shipperRole.setName("shipper");
			roleService.save(shipperRole);
		}
		
		if(customerRole == null) {
			customerRole = new RoleEntity();
			customerRole.setName("customer");
			roleService.save(customerRole);
		}
	}
	
	public void createAdmin() {
		UserDTO admin = userService.findByEmail("admin@gmail.com");
		if(admin == null) {
			admin = new UserDTO();
			admin.setEmail("admin@gmail.com");
			admin.setName("admin");
			admin.setPassword("12345");
			admin.setAddress("123");
			admin.setAvatar("123");
			userService.save(admin, "admin");
		}
	}

}
