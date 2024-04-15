package com.luanvan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luanvan.dto.CommentDTO;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.pagination.CommentOutput;
import com.luanvan.service.CommentService;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public CommentDTO save(@RequestBody CommentDTO dto) {
	 commentService.save(dto);
		return new CommentDTO();
	}
	
	@GetMapping
	public CommentOutput getComments(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "blogId", defaultValue = "none") String strBlogId,
			@RequestParam(name = "productId", defaultValue = "none") String strProductId) {
		CommentOutput result = new CommentOutput();
		result.setPage(page);
		result.setListResult(commentService.findAll((page - 1) * limit, limit, strBlogId, strProductId));
		result.setTotalPage((int) Math.ceil((double) commentService.totalItem(strBlogId, strProductId) / limit));
		return result;
	}
	
	@PostMapping("/like/{commentId}/{userId}")
	public ResponseEntity<?> likeBlog(@PathVariable Long commentId, @PathVariable Long userId) {
		commentService.like(commentId, userId);
		return ResponseEntity.ok(new SuccessResponse());
	}
	
	@PostMapping("/unlike/{commentId}/{userId}")
	public ResponseEntity<?> unLikeBlog(@PathVariable Long commentId, @PathVariable Long userId){
		commentService.unLike(commentId, userId);
		return ResponseEntity.ok(new SuccessResponse());
	}
	
}
