package com.luanvan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "imports")
public class ImportEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date createdTime;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	private UserEntity employee;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "_import", orphanRemoval = true)
	private List<ImportDetailEntity> importDetails = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name = "warehouse_id")
	private WarehouseEntity warehouse;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private SupplierEntity supplier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public List<ImportDetailEntity> getImportDetails() {
		return importDetails;
	}

	public void setImportDetails(List<ImportDetailEntity> importDetails) {
		this.importDetails = importDetails;
	}

	public WarehouseEntity getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(WarehouseEntity warehouse) {
		this.warehouse = warehouse;
	}

	public SupplierEntity getSupplier() {
		return supplier;
	}

	public void setSupplier(SupplierEntity supplier) {
		this.supplier = supplier;
	}
	
	
}
