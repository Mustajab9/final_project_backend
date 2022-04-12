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

import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetByUserIdDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.service.ThreadLikeService;

@RestController
@RequestMapping("thread-likes")
public class ThreadLikeController {
	private ThreadLikeService threadLikeService;
	
	@Autowired
	private ThreadLikeController(ThreadLikeService threadLikeService) {
		this.threadLikeService = threadLikeService;
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadLikeDtoRes> insertData(@RequestBody @Valid InsertThreadLikeDtoReq data) throws Exception{
		InsertThreadLikeDtoRes insertData = threadLikeService.insert(data);
		return new ResponseEntity<InsertThreadLikeDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<GetAllThreadLikeDtoRes> getAll() throws Exception{
		GetAllThreadLikeDtoRes getAll = threadLikeService.findAll();
		return new ResponseEntity<GetAllThreadLikeDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByThreadLikeIdDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByThreadLikeIdDtoRes getById = threadLikeService.findById(id);
		return new ResponseEntity<GetByThreadLikeIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadLikeIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception{
		DeleteByThreadLikeIdDtoRes deleteById = threadLikeService.deleteById(id);
		return new ResponseEntity<DeleteByThreadLikeIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("thread/{id}")
	public ResponseEntity<GetThreadLikeByThreadDtoRes> getByThread(@PathVariable("id") String id) throws Exception{
		GetThreadLikeByThreadDtoRes getByThread = threadLikeService.findByThread(id);
		return new ResponseEntity<GetThreadLikeByThreadDtoRes>(getByThread, HttpStatus.OK);
	}
	
	@GetMapping("thread/{userId}/{threadId}")
	public ResponseEntity<GetThreadLikeByThreadDtoRes> getByThreadAndUser(@PathVariable("userId") String userId, @PathVariable("threadId") String threadId) throws Exception{
		GetThreadLikeByThreadDtoRes getByThread = threadLikeService.findByThreadAndUser(userId, threadId);
		return new ResponseEntity<GetThreadLikeByThreadDtoRes>(getByThread, HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<GetByUserIdDtoRes> getByUser() throws Exception{
		GetByUserIdDtoRes getByUser = threadLikeService.findByUser();
		return new ResponseEntity<GetByUserIdDtoRes>(getByUser, HttpStatus.OK);
	}
}
