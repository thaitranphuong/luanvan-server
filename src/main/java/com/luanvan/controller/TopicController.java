package com.luanvan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.luanvan.dto.TopicDTO;
import com.luanvan.dto.pagination.TopicOutput;
import com.luanvan.service.TopicService;

@RestController
@CrossOrigin
@RequestMapping("/topic")
public class TopicController {
	@Autowired
	private TopicService topicService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public TopicDTO save(@RequestBody TopicDTO dto) {
		topicService.save(dto);
		return new TopicDTO();
	}
	
	@GetMapping
	public TopicOutput getTopics(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		TopicOutput result = new TopicOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(topicService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) topicService.totalItem(name) / limit));
		} else {
			result.setListResult(topicService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) topicService.totalItem() / limit));
		}
		return result;
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public TopicDTO delete(@PathVariable Long id) {
		topicService.delete(id);
		return new TopicDTO();
	}
	
	@GetMapping("/{id}")
	public TopicDTO getTopic(@PathVariable Long id) {
		return topicService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<TopicDTO> getAllTopics() {
		return topicService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public TopicDTO update(@RequestBody TopicDTO dto) {
		topicService.update(dto);
		return new TopicDTO();
	}
}
