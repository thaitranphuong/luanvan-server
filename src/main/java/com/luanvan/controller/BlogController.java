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

import com.luanvan.dto.BlogDTO;
import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.pagination.BlogOutput;
import com.luanvan.service.BlogService;

@RestController
@CrossOrigin
@RequestMapping("/blog")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestPart("blog") BlogDTO blog , @RequestPart("thumbnail") MultipartFile thumbnail) {
		if (blogService.save(blog, thumbnail))
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping
	public BlogOutput getBlogs(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name,
			@RequestParam(name = "topicId", defaultValue = "none") String strTopicId) {
		BlogOutput result = new BlogOutput();
		result.setPage(page);
		if (!strTopicId.equals("none")) {
			Long topicId = Long.valueOf(strTopicId);
			if (!name.equals("none")) {
				result.setListResult(blogService.findAll((page - 1) * limit, limit, name, topicId));
				result.setTotalPage((int) Math.ceil((double) blogService.totalItem(name, topicId) / limit));
			} else {
				result.setListResult(blogService.findAll((page - 1) * limit, limit, topicId));
				result.setTotalPage((int) Math.ceil((double) blogService.totalItem(topicId) / limit));
			}
		} else {
			if (!name.equals("none")) {
				result.setListResult(blogService.findAll((page - 1) * limit, limit, name));
				result.setTotalPage((int) Math.ceil((double) blogService.totalItem(name) / limit));
			} else {
				result.setListResult(blogService.findAll((page - 1) * limit, limit));
				result.setTotalPage((int) Math.ceil((double) blogService.totalItem() / limit));
			}
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public BlogDTO delete(@PathVariable Long id) {
		blogService.delete(id);
		return new BlogDTO();
	}
	
	@GetMapping("/{id}")
	public BlogDTO getBlog(@PathVariable Long id) {
		return blogService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<BlogDTO> getAllBlogs() {
		return blogService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public BlogDTO update(@RequestBody BlogDTO dto) {
		blogService.update(dto);
		return new BlogDTO();
	}
}
