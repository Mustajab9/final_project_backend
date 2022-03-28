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

import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;
import com.lawencon.community.service.ThreadCommentService;

@RestController
@RequestMapping("thread-comments")
public class ThreadCommentController {
	private ThreadCommentService threadCommentService;

	@Autowired
	private ThreadCommentController(ThreadCommentService threadCommentService) {
		this.threadCommentService = threadCommentService;
	}

	@PostMapping
	public ResponseEntity<InsertThreadCommentDtoRes> insertData(@RequestBody @Valid InsertThreadCommentDtoReq data) throws Exception {
		InsertThreadCommentDtoRes insertData = threadCommentService.insert(data);
		return new ResponseEntity<InsertThreadCommentDtoRes>(insertData, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<GetAllThreadCommentDtoRes> getAll() throws Exception {
		GetAllThreadCommentDtoRes getAll = threadCommentService.findAll();
		return new ResponseEntity<GetAllThreadCommentDtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByThreadCommentIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByThreadCommentIdDtoRes getById = threadCommentService.findById(id);
		return new ResponseEntity<GetByThreadCommentIdDtoRes>(getById, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadCommentIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByThreadCommentIdDtoRes deleteById = threadCommentService.deleteById(id);
		return new ResponseEntity<DeleteByThreadCommentIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("thread/{id}")
	public ResponseEntity<GetThreadCommentByThreadDtoRes> getByThread(@PathVariable("id") String id) throws Exception {
		GetThreadCommentByThreadDtoRes getByThread = threadCommentService.findByThread(id);
		return new ResponseEntity<GetThreadCommentByThreadDtoRes>(getByThread, HttpStatus.OK);
	}
}
