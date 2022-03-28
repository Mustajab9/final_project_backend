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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.threadtype.DeleteByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;
import com.lawencon.community.service.ThreadTypeService;

@RestController
@RequestMapping("users")
public class ThreadTypeController {
	private ThreadTypeService threadTypeService;
	
	@Autowired
	private ThreadTypeController(ThreadTypeService threadTypeService) {
		this.threadTypeService = threadTypeService;
	}
	
	@PostMapping
	public ResponseEntity<InsertThreadTypeDtoRes> insertData(@RequestBody @Valid InsertThreadTypeDtoReq data) throws Exception{
		InsertThreadTypeDtoRes insertData = threadTypeService.insert(data);
		return new ResponseEntity<InsertThreadTypeDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateThreadTypeDtoRes> updateData(@RequestBody @Valid UpdateThreadTypeDtoReq data) throws Exception{
		UpdateThreadTypeDtoRes updateData = threadTypeService.update(data);
		return new ResponseEntity<UpdateThreadTypeDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllThreadTypeDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception{
		GetAllThreadTypeDtoRes getAll = threadTypeService.findAll(start, max);
		return new ResponseEntity<GetAllThreadTypeDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByThreadTypeIdDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByThreadTypeIdDtoRes getById = threadTypeService.findById(id);
		return new ResponseEntity<GetByThreadTypeIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByThreadTypeIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception{
		DeleteByThreadTypeIdDtoRes deleteById = threadTypeService.deleteById(id);
		return new ResponseEntity<DeleteByThreadTypeIdDtoRes>(deleteById, HttpStatus.OK);
	}
}
