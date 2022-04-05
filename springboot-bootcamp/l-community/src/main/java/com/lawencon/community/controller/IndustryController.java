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

import com.lawencon.community.dto.industry.DeleteByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;
import com.lawencon.community.service.IndustryService;

@RestController
@RequestMapping("industries")
public class IndustryController {
	private IndustryService industryService;

	@Autowired
	public void setIndustryService(IndustryService industryService) {
		this.industryService = industryService;
	}

	@GetMapping
	public ResponseEntity<GetAllIndustryDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllIndustryDtoRes result = industryService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllIndustryDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByIndustryIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByIndustryIdDtoRes data = industryService.findById(id);
		return new ResponseEntity<GetByIndustryIdDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByIndustryIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByIndustryIdDtoRes data = industryService.deleteById(id);
		return new ResponseEntity<DeleteByIndustryIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertIndustryDtoRes> insertData(@RequestBody @Valid InsertIndustryDtoReq data) throws Exception {
		InsertIndustryDtoRes insert = industryService.insert(data);
		return new ResponseEntity<InsertIndustryDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateIndustryDtoRes> updateData(@RequestBody @Valid UpdateIndustryDtoReq data) throws Exception {
		UpdateIndustryDtoRes update = industryService.update(data);
		return new ResponseEntity<UpdateIndustryDtoRes>(update, HttpStatus.OK);
	}
}
