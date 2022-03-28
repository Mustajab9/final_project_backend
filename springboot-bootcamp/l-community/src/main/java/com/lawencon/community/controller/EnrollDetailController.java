package com.lawencon.community.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEventIdDtoRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoRes;
import com.lawencon.community.service.EnrollDetailService;

@RestController
@RequestMapping("enroll-details")
public class EnrollDetailController {
	private EnrollDetailService enrollDetailService;

	@Autowired
	public void setBookmarkService(EnrollDetailService enrollDetailService) {
		this.enrollDetailService = enrollDetailService;
	}

	@GetMapping
	public ResponseEntity<GetAllEnrollDetailDtoRes> getAll() throws Exception {
		GetAllEnrollDetailDtoRes result = enrollDetailService.findAll();
		return new ResponseEntity<GetAllEnrollDetailDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByEnrollDetailIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByEnrollDetailIdDtoRes data = enrollDetailService.findById(id);
		return new ResponseEntity<GetByEnrollDetailIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("event/{id}")
	public ResponseEntity<GetByEventIdDtoRes> getByEvent(@PathVariable("id") String id) throws Exception {
		GetByEventIdDtoRes data = enrollDetailService.findByEvent(id);
		return new ResponseEntity<GetByEventIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEnrollDetailDtoRes> insertData(@RequestBody @Valid InsertEnrollDetailDtoReq data) throws Exception {
		InsertEnrollDetailDtoRes insert = enrollDetailService.insert(data);
		return new ResponseEntity<InsertEnrollDetailDtoRes>(insert, HttpStatus.CREATED);
	}
}
