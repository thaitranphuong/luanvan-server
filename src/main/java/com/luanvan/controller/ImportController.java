package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.ImportDTO;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.pagination.ImportOutput;
import com.luanvan.service.ImportService;

@RestController
@CrossOrigin
@RequestMapping("/import")
public class ImportController {
	@Autowired
	private ImportService importService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody ImportDTO dto) {
		if (importService.save(dto) != null)
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping
	public ImportOutput getImports(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "id", defaultValue = "none") String strId) {
		ImportOutput result = new ImportOutput();
		result.setPage(page);
		if (!strId.equals("none")) {
			Long id = Long.valueOf(strId);
			result.setListResult(importService.findAll((page - 1) * limit, limit, id));
			result.setTotalPage((int) Math.ceil((double) importService.totalItem(id) / limit));
		} else {
			result.setListResult(importService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) importService.totalItem() / limit));
		}
		return result;
	}
	
	@GetMapping("/{id}")
	public ImportDTO getImport(@PathVariable Long id) {
		return importService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<ImportDTO> getAllImports() {
		return importService.findAll();
	}
}
