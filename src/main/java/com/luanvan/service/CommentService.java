package com.luanvan.service;

import java.util.List;

import com.luanvan.dto.CommentDTO;

public interface CommentService {
	void save(CommentDTO comment);
	List<CommentDTO> findAll(int page, int limit, String strBlogId, String strProductId);
	int totalItem(String strBlogId, String strProductId);
	public void like(Long commentId, Long userId);
	public void unLike(Long commentId, Long userId);
}
