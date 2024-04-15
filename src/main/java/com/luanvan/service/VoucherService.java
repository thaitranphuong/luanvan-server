package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.VoucherDTO;

public interface VoucherService {
	boolean save(VoucherDTO voucher);
	void update(VoucherDTO voucher);
	List<VoucherDTO> findAll();
	List<VoucherDTO> findAll(Long userId);
	List<VoucherDTO> findAll(int page, int limit);
	List<VoucherDTO> findAll(int page, int limit, String name);
	List<VoucherDTO> findAll(int page, int limit, Long userId);
	int totalItem();
	int totalItem(String name);
	int totalItem(Long userId);
	void delete(Long id);
	VoucherDTO findById(Long id);
	boolean collect(Long userId, Long voucherId);
}
