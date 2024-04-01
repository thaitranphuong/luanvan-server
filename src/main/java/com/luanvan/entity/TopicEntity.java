package com.luanvan.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "topics")
public class TopicEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String code;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "topic", orphanRemoval = true)
	private List<BlogEntity> blogs = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<BlogEntity> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<BlogEntity> blogs) {
		this.blogs = blogs;
	}
	
	
}
