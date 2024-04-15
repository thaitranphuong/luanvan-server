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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.BannerDTO;
import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.pagination.BannerOutput;
import com.luanvan.service.BannerService;

@RestController
@CrossOrigin
@RequestMapping("/banner")
public class BannerController {
	@Autowired
	private BannerService bannerService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestPart("banner") BannerDTO banner , @RequestPart("image") MultipartFile image) {
		if (bannerService.save(banner, image))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping
	public BannerOutput getBanners(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		BannerOutput result = new BannerOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(bannerService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) bannerService.totalItem(name) / limit));
		} else {
			result.setListResult(bannerService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) bannerService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public BannerDTO delete(@PathVariable Long id) {
		bannerService.delete(id);
		return new BannerDTO();
	}
	
	@GetMapping("/{id}")
	public BannerDTO getBanner(@PathVariable Long id) {
		return bannerService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<BannerDTO> getAllBanners() {
		return bannerService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public BannerDTO update(@RequestBody BannerDTO dto) {
		bannerService.update(dto);
		return new BannerDTO();
	}
}
