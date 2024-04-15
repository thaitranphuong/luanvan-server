package com.luanvan.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImportDTO {
	private Long id;
	private String createdTime;
	private Long employeeId;
	private Long warehouseId;
	private Long supplierId;
	private String employeeName;
	private String warehouseName;
	private String supplierName;
	private List<ImportDetailDTO> importDetails = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		this.createdTime = formatter.format(createdTime);
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public Long getWarehouseId() {
		return warehouseId;
	}
	public void setWarehouseId(Long warehouseId) {
		this.warehouseId = warehouseId;
	}
	public Long getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}
	public List<ImportDetailDTO> getImportDetails() {
		return importDetails;
	}
	public void setImportDetails(List<ImportDetailDTO> importDetails) {
		this.importDetails = importDetails;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
