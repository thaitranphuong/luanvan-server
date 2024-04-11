package com.luanvan.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public boolean save(String directory, MultipartFile file) {
		Path path = Paths.get("src/main/resources/static/" + directory +"/");
		try {
			InputStream inputStream = file.getInputStream();
			Files.copy(inputStream, path.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			return true;
		} catch (IOException e) {
			return false;
		}
	}

}
