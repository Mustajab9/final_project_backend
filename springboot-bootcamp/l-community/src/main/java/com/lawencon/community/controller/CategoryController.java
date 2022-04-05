package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.category.DeleteByCategoryIdDtoRes;
import com.lawencon.community.dto.category.GetAllCategoryDtoRes;
import com.lawencon.community.dto.category.GetByCategoryIdDtoRes;
import com.lawencon.community.dto.category.InsertCategoryDtoReq;
import com.lawencon.community.dto.category.InsertCategoryDtoRes;
import com.lawencon.community.dto.category.UpdateCategoryDtoReq;
import com.lawencon.community.dto.category.UpdateCategoryDtoRes;
import com.lawencon.community.service.CategoryService;

@RestController
@RequestMapping("categories")
public class CategoryController {
	private CategoryService categoryService;

	@Autowired
	public void setBookmarkService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@GetMapping
	public ResponseEntity<GetAllCategoryDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllCategoryDtoRes result = categoryService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllCategoryDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByCategoryIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByCategoryIdDtoRes data = categoryService.findById(id);
		return new ResponseEntity<GetByCategoryIdDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByCategoryIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByCategoryIdDtoRes data = categoryService.deleteById(id);
		return new ResponseEntity<DeleteByCategoryIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertCategoryDtoRes> insertData(@RequestBody @Valid InsertCategoryDtoReq data) throws Exception {
		InsertCategoryDtoRes insert = categoryService.insert(data);
		return new ResponseEntity<InsertCategoryDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateCategoryDtoRes> updateData(@RequestBody @Valid UpdateCategoryDtoReq data) throws Exception {
		UpdateCategoryDtoRes update = categoryService.update(data);
		return new ResponseEntity<UpdateCategoryDtoRes>(update, HttpStatus.OK);
	}
}
