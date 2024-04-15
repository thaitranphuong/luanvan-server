package com.luanvan.controller;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.luanvan.dto.BrandDTO;
import com.luanvan.dto.ErrorResponse;
import com.luanvan.dto.ImageDTO;
import com.luanvan.dto.ProductDTO;
import com.luanvan.dto.ProductRateDTO;
import com.luanvan.dto.SuccessResponse;
import com.luanvan.dto.pagination.BrandOutput;
import com.luanvan.dto.pagination.ProductOutput;
import com.luanvan.service.FileService;
import com.luanvan.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestPart("product") ProductDTO product , @RequestPart("thumbnail") MultipartFile thumbnail) {
		Long productId = productService.save(product, thumbnail);
		if (productId != null)
			return ResponseEntity.ok(productId);
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@PostMapping("/image-fulldescription")
	@PreAuthorize("hasRole('ADMIN')")
	public ImageDTO saveImageFulldescription(@RequestPart("image") MultipartFile image) throws IOException {
		if(image.isEmpty()) return null;
		Path path = Paths.get("src/main/resources/static/ckeditor_images/");
		InputStream inputStream = image.getInputStream();
		Files.copy(inputStream, path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		ImageDTO result = new ImageDTO();
		result.setName(image.getOriginalFilename());
		return result;
	}
	
	@GetMapping("/customer-page")
	@PreAuthorize("hasRole('CUSTOMER')")
	public ProductOutput getProducts(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name,
			@RequestParam(name = "categoryId", defaultValue = "none") String strCategoryId,
			@RequestParam(name = "brandId", defaultValue = "none") String strBrandId) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(productService.findAllCustomerPage((page - 1) * limit, limit, name, strCategoryId, strBrandId));
			result.setTotalPage((int) Math.ceil((double) productService.totalItemCustomerPage(name, strCategoryId, strBrandId) / limit));
		} else {
			result.setListResult(productService.findAllCustomerPage((page - 1) * limit, limit, strCategoryId, strBrandId));
			result.setTotalPage((int) Math.ceil((double) productService.totalItemCustomerPage(strCategoryId, strBrandId) / limit));
		}
		return result;
	}
	
	@GetMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ProductOutput getProducts(@RequestParam(name = "page") int page, @RequestParam(name = "limit") int limit,
			@RequestParam(name = "name", defaultValue = "none") String name) {
		ProductOutput result = new ProductOutput();
		result.setPage(page);
		if (!name.equals("none")) {
			result.setListResult(productService.findAll((page - 1) * limit, limit, name));
			result.setTotalPage((int) Math.ceil((double) productService.totalItem(name) / limit));
		} else {
			result.setListResult(productService.findAll((page - 1) * limit, limit));
			result.setTotalPage((int) Math.ceil((double) productService.totalItem() / limit));
		}
		return result;
	}
	
	@GetMapping("/get-best-sales")
	public List<ProductDTO> getBestSales() {
		List<ProductDTO> result = productService.findBestSales();
		return result;
	}
	
	@GetMapping("/get-new-products")
	public List<ProductDTO> getNewProducts() {
		List<ProductDTO> result = productService.findNewProducts();
		return result;
	}
	
	@GetMapping("/{id}")
	public ProductDTO getProduct(@PathVariable Long id) {
		return productService.findById(id);
	}
	
	@GetMapping("/get-all")
	public List<ProductDTO> getAllProducts() {
		return productService.findAll();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ProductDTO update(@RequestBody ProductDTO product) {
		productService.update(product);
		return new ProductDTO();
	}
	
	@PostMapping("/updateThumbnail")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?>  updateThumbnail(@RequestPart("thumbnail") MultipartFile thumbnail) {
		boolean result = fileService.save("products", thumbnail);
		if (result)
			return ResponseEntity.ok(new SuccessResponse());
		return ResponseEntity.badRequest().body(new ErrorResponse());
	}
	
	@GetMapping("/get-rate/{id}")
	public ProductRateDTO getRate(@PathVariable Long id) {
		return productService.findRate(id);
	}
}
