package com.luanvan.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.ImportDTO;
import com.luanvan.entity.ImportEntity;
import com.luanvan.entity.SupplierEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.entity.WarehouseEntity;
import com.luanvan.repository.SupplierRepository;
import com.luanvan.repository.UserRepository;
import com.luanvan.repository.WarehouseRepository;

@Component
public class ImportConverter {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	public ImportEntity toEntity(ImportDTO dto) {
		ImportEntity entity = new ImportEntity();
		UserEntity employee = userRepository.findById(dto.getEmployeeId()).get();
		WarehouseEntity warehouse = warehouseRepository.findById(dto.getWarehouseId()).get();
		SupplierEntity supplier = supplierRepository.findById(dto.getSupplierId()).get();
		entity.setEmployee(employee);
		entity.setWarehouse(warehouse);
		entity.setSupplier(supplier);
		return entity;
	}
	
	public ImportDTO toDTO(ImportEntity entity) {
		ImportDTO dto = new ImportDTO();
		dto.setId(entity.getId());
		dto.setCreatedTime(entity.getCreatedTime());
		UserEntity employee = userRepository.findById(entity.getEmployee().getId()).get();
		WarehouseEntity warehouse = warehouseRepository.findById(entity.getWarehouse().getId()).get();
		SupplierEntity supplier = supplierRepository.findById(entity.getSupplier().getId()).get();
		dto.setEmployeeName(employee.getName());
		dto.setWarehouseName(warehouse.getName());
		dto.setSupplierName(supplier.getName());
		return dto;
	}
}
