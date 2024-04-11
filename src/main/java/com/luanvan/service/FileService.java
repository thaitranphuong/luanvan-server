package com.luanvan.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
	boolean save(String directory, MultipartFile file);
}
