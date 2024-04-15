package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.VoucherDTO;
import com.luanvan.dto.pagination.VoucherOutput;
import com.luanvan.service.VoucherService;

@RestController
@CrossOrigin
@RequestMapping("/voucher")
public class VoucherController {
	@Autowired
	private VoucherService voucherService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody VoucherDTO dto) {
		if (voucherService.save(dto))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping
	public VoucherOutput getVouchers(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		VoucherOutput result = new VoucherOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(voucherService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) voucherService.totalItem(name) / limit));
		} else {
			result.setListResult(voucherService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) voucherService.totalItem() / limit));
		}
		return result;
	}
	
	@GetMapping("/get-by-user/{userId}")
	public VoucherOutput getVouchersByUserId(@RequestParam(name = "page", defaultValue = "none") String strPage,
			@RequestParam(name = "limit", defaultValue = "none") String strLimit,
			@PathVariable Long userId) {
		VoucherOutput result = new VoucherOutput();
		//Vouchers chua luu
		if (!strPage.equals("none")) {
			int page = Integer.valueOf(strPage);
			int limit = Integer.valueOf(strLimit);
			result.setPage(page);
			result.setListResult(voucherService.findAll((page - 1) * limit, limit, userId));
			result.setTotalPage((int) Math.ceil((double) voucherService.totalItem(userId) / limit));
		}
		//Vouchers da luu
		else {
			result.setPage(1);
			result.setTotalPage(1);
			result.setListResult(voucherService.findAll(userId));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public VoucherDTO delete(@PathVariable Long id) {
		voucherService.delete(id);
		return new VoucherDTO();
	}
	
	@GetMapping("/{id}")
	public VoucherDTO getVoucher(@PathVariable Long id) {
		return voucherService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<VoucherDTO> getAllVouchers() {
		return voucherService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public VoucherDTO update(@RequestBody VoucherDTO dto) {
		voucherService.update(dto);
		return new VoucherDTO();
	}
	
	@PostMapping("/{userId}/{voucherId}")
	public ResponseEntity<?> collect(@PathVariable Long userId, @PathVariable Long voucherId) {
		if (voucherService.collect(userId, voucherId))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
