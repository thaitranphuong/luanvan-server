package com.luanvan.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.service.FileService;

@RestController
@CrossOrigin
public class ImageController {
	@Autowired
	private FileService fileService;
	
	@GetMapping("/getimage/{directory}/{photo}")
	@ResponseBody
	public ResponseEntity<ByteArrayResource> getImage(@PathVariable("directory") String directory, @PathVariable("photo") String photo) {
		if(!photo.equals("") || photo != null) {
			try {
				Path filename = Paths.get("src/main/resources/static/" + directory, photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok()
						.contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png"))
						.body(byteArrayResource);
			} catch (Exception e) {
				
			}
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/uploadimage/{directory}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  updateThumbnail(@PathVariable("directory") String directory, @RequestPart("image") MultipartFile image) {
		boolean result = fileService.save(directory, image);
		if (result)
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
}
