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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.thread.DeleteByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.service.ThreadService;

@RestController
@RequestMapping("threads")
public class ThreadController {
	private ThreadService threadService;
	
	@Autowired
	private ThreadController(ThreadService threadService) {
		this.threadService = threadService;
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadDtoRes> insertData(@RequestPart(name = "content") String content, @RequestPart(required = false) MultipartFile[] file) throws Exception{
		InsertThreadDtoRes insertData = threadService.insert(content, file);
		return new ResponseEntity<InsertThreadDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateThreadDtoRes> updateData(@RequestBody @Valid UpdateThreadDtoReq data) throws Exception{
		UpdateThreadDtoRes updateData = threadService.update(data);
		return new ResponseEntity<UpdateThreadDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllThreadDtoRes> getAll(Integer startPage, Integer maxPage) throws Exception{
		GetAllThreadDtoRes getAll = threadService.findAll(startPage, maxPage);
		return new ResponseEntity<GetAllThreadDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByThreadIdDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByThreadIdDtoRes getById = threadService.findById(id);
		return new ResponseEntity<GetByThreadIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception{
		DeleteByThreadIdDtoRes deleteById = threadService.deleteById(id);
		return new ResponseEntity<DeleteByThreadIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<GetThreadByUserDtoRes> getByUser() throws Exception{
		GetThreadByUserDtoRes getByUser = threadService.findByUser();
		return new ResponseEntity<GetThreadByUserDtoRes>(getByUser, HttpStatus.OK);
	}
	
	@GetMapping("category/{id}")
	public ResponseEntity<GetThreadByCategoryDtoRes> getByCategory(@PathVariable("id") String[] id) throws Exception{
		GetThreadByCategoryDtoRes getByCategory = threadService.findByCategory(id);
		return new ResponseEntity<GetThreadByCategoryDtoRes>(getByCategory, HttpStatus.OK);
	}
}
