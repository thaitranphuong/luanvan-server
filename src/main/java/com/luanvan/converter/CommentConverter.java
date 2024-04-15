package com.luanvan.converter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.luanvan.dto.CommentDTO;
import com.luanvan.entity.CommentEntity;
import com.luanvan.entity.UserEntity;
import com.luanvan.entity.BlogEntity;
import com.luanvan.entity.ProductEntity;
import com.luanvan.repository.BlogRepository;
import com.luanvan.repository.ProductRepository;
import com.luanvan.repository.UserRepository;

@Component
public class CommentConverter {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public CommentEntity toEntity(CommentDTO dto) {
		CommentEntity entity = new CommentEntity();
		entity.setContent(dto.getContent());
		entity.setImage(dto.getImage());
		entity.setStar(dto.getStar());
		UserEntity user = userRepository.findById(dto.getUserId()).orElse(null);
		entity.setUser(user);
		if(dto.getBlogId() != null) {
			BlogEntity blog = blogRepository.findById(dto.getBlogId()).orElse(null);
			entity.setBlog(blog);
		}
		if(dto.getProductId() != null) {
			ProductEntity product = productRepository.findById(dto.getProductId()).orElse(null);
			entity.setProduct(product);
		}
		return entity;
	}
	
	public CommentDTO toDTO(CommentEntity entity) {
		CommentDTO dto = new CommentDTO();
		dto.setId(entity.getId());
		dto.setContent(entity.getContent());
		dto.setImage(entity.getImage());
		dto.setStar(entity.getStar());
		dto.setCreatedTime(entity.getCreatedTime());
		UserEntity user = entity.getUser();
		dto.setUserName(user.getName());
		dto.setUserAvatar(user.getAvatar());
		Set<Long> userLikeIds = new HashSet<>();
		entity.getUsers().forEach(item -> {
			userLikeIds.add(item.getId());
		});
		dto.setUserLikeIds(userLikeIds);
		return dto;
	}
}
