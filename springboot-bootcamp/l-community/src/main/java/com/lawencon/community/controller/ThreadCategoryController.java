package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.threadcategory.DeleteByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetAllThreadCategoryDtoRes;
import com.lawencon.community.dto.threadcategory.GetByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetThreadCategoryByThreadDtoRes;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoRes;
import com.lawencon.community.service.ThreadCategoryService;

@RestController
@RequestMapping("thread-categories")
public class ThreadCategoryController {
	private ThreadCategoryService threadCategoryService;

	@Autowired
	private ThreadCategoryController(ThreadCategoryService threadCategoryService) {
		this.threadCategoryService = threadCategoryService;
	}

	@PostMapping
	public ResponseEntity<InsertThreadCategoryDtoRes> insertData(@RequestBody @Valid InsertThreadCategoryDtoReq data) throws Exception {
		InsertThreadCategoryDtoRes insertData = threadCategoryService.insert(data);
		return new ResponseEntity<InsertThreadCategoryDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllThreadCategoryDtoRes> getAll() throws Exception {
		GetAllThreadCategoryDtoRes getAll = threadCategoryService.findAll();
		return new ResponseEntity<GetAllThreadCategoryDtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByThreadCategoryIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByThreadCategoryIdDtoRes getById = threadCategoryService.findById(id);
		return new ResponseEntity<GetByThreadCategoryIdDtoRes>(getById, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadCategoryIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByThreadCategoryIdDtoRes deleteById = threadCategoryService.deleteById(id);
		return new ResponseEntity<DeleteByThreadCategoryIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("thread/{id}")
	public ResponseEntity<GetThreadCategoryByThreadDtoRes> getByThread(@PathVariable("id") String id) throws Exception {
		GetThreadCategoryByThreadDtoRes getByThread = threadCategoryService.findByThread(id);
		return new ResponseEntity<GetThreadCategoryByThreadDtoRes>(getByThread, HttpStatus.OK);
	}
}
