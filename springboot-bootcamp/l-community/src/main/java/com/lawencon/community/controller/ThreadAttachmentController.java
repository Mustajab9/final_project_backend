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

import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetThreadAttachmentByThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;
import com.lawencon.community.service.ThreadAttachmentService;

@RestController
@RequestMapping("thread-attachments")
public class ThreadAttachmentController {
	private ThreadAttachmentService threadAttachmentService;

	@Autowired
	private ThreadAttachmentController(ThreadAttachmentService threadAttachmentService) {
		this.threadAttachmentService = threadAttachmentService;
	}

	@PostMapping
	public ResponseEntity<InsertThreadAttachmentDtoRes> insertData(@RequestBody @Valid InsertThreadAttachmentDtoReq data) throws Exception {
		InsertThreadAttachmentDtoRes insertData = threadAttachmentService.insert(data);
		return new ResponseEntity<InsertThreadAttachmentDtoRes>(insertData, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<GetAllThreadAttachmentDtoRes> getAll() throws Exception {
		GetAllThreadAttachmentDtoRes getAll = threadAttachmentService.findAll();
		return new ResponseEntity<GetAllThreadAttachmentDtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByThreadAttachmentIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByThreadAttachmentIdDtoRes getById = threadAttachmentService.findById(id);
		return new ResponseEntity<GetByThreadAttachmentIdDtoRes>(getById, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadAttachmentIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByThreadAttachmentIdDtoRes deleteById = threadAttachmentService.deleteById(id);
		return new ResponseEntity<DeleteByThreadAttachmentIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("thread/{id}")
	public ResponseEntity<GetThreadAttachmentByThreadDtoRes> getByThread(@PathVariable("id") String id) throws Exception {
		GetThreadAttachmentByThreadDtoRes getByThread = threadAttachmentService.findByThread(id);
		return new ResponseEntity<GetThreadAttachmentByThreadDtoRes>(getByThread, HttpStatus.OK);
	}
}
