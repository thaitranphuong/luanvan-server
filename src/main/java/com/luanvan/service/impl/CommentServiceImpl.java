package com.luanvan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luanvan.converter.CommentConverter;
import com.luanvan.dto.CommentDTO;
import com.luanvan.entity.CommentEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.repository.CommentRepository;
import com.luanvan.repository.UserRepository;
import com.luanvan.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentRepository commentRepo;

	@Autowired
	private CommentConverter commentConverter;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(CommentDTO dto) {
		commentRepo.save(commentConverter.toEntity(dto));
	}

	@Override
	public List<CommentDTO> findAll(int page, int limit, String strBlogId, String strProductId) {
		List<CommentEntity> entities = new ArrayList<>();
		if(!strBlogId.equals("none"))
			entities = commentRepo.findAllByBlogId(strBlogId, page, limit);
		if(!strProductId.equals("none"))
			entities = commentRepo.findAllByProductId(strProductId, page, limit);
		List<CommentDTO> DTOs = new ArrayList<>();
		for(CommentEntity item: entities) {
			CommentDTO dto = commentConverter.toDTO(item);
			DTOs.add(dto);
		}
		return DTOs;
	}
	
	@Override
	public int totalItem(String strBlogId, String strProductId) {
		List<CommentEntity> result = new ArrayList<>();
		if(!strBlogId.equals("none"))
			result = commentRepo.findAllByBlogId(strBlogId);
		if(!strProductId.equals("none"))
			result = commentRepo.findAllByProductId(strProductId);
		return result.size();
	}

	@Override
	public void like(Long commentId, Long userId) {
		CommentEntity comment = commentRepo.findById(commentId).get();
		UserEntity user = userRepository.findById(userId).get();
		user.getLikedComments().add(comment);
		comment.getUsers().add(user);
		commentRepo.save(comment);
	}

	@Override
	public void unLike(Long commentId, Long userId) {
		CommentEntity comment = commentRepo.findById(commentId).get();
		UserEntity user = userRepository.findById(userId).get();
		user.getLikedComments().remove(comment);
		comment.getUsers().remove(user);
		commentRepo.save(comment);
		userRepository.save(user);
	}
}
