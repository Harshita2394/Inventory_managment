package com.project.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.inventorymanagement.dto.ArticleDto;
import com.project.inventorymanagement.dto.ProductDto;
import com.project.inventorymanagement.service.InventoryManagementService;

@RestController
public class InventoryManagementController {
	
	@Autowired
	private InventoryManagementService service;

	@PostMapping(value = "/save/Article")
	private ResponseEntity<String> saveArticle(@RequestBody ArticleDto article){
		service.saveArticle(article);
		return new ResponseEntity<>("Article saved", HttpStatus.CREATED);
	}
	
	@PostMapping(value ="save/Product")
	private ResponseEntity<String> saveProduct(@RequestBody ProductDto productDto){
		service.saveProduct(productDto);
		return new ResponseEntity<>("Product saved", HttpStatus.CREATED);
	} 
	
	@GetMapping(value="get/Products")
	private ResponseEntity<List<ProductDto>> getAllProducts(){
		List<ProductDto> productList = service.getAllProducts();
		if(productList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ProductDto>>(productList, HttpStatus.OK);
	}
	
	@PutMapping(value="sell/Product")
	private ResponseEntity<String> sellProduct(@RequestBody ProductDto dto){
		service.sellProduct(dto);
		return new ResponseEntity<String>("Product inventory updated", HttpStatus.OK);
		
	}
}
