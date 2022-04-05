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

import com.lawencon.community.dto.regency.DeleteByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.GetAllRegencyDtoRes;
import com.lawencon.community.dto.regency.GetByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.InsertRegencyDtoReq;
import com.lawencon.community.dto.regency.InsertRegencyDtoRes;
import com.lawencon.community.dto.regency.UpdateRegencyDtoReq;
import com.lawencon.community.dto.regency.UpdateRegencyDtoRes;
import com.lawencon.community.service.RegencyService;

@RestController
@RequestMapping("regencies")
public class RegencyController {

private RegencyService regencyService;
	
	@Autowired
	public RegencyController(RegencyService regencyService) {
		this.regencyService=regencyService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllRegencyDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllRegencyDtoRes regencies = regencyService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllRegencyDtoRes>(regencies, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByRegencyIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByRegencyIdDtoRes regency = regencyService.findById(id);
		return new ResponseEntity<GetByRegencyIdDtoRes>(regency, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByRegencyIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByRegencyIdDtoRes deleteRegency = regencyService.deleteById(id);
		return new ResponseEntity<DeleteByRegencyIdDtoRes>(deleteRegency, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRegencyDtoRes> save(@RequestBody @Valid InsertRegencyDtoReq dtoReq) throws Exception {
		InsertRegencyDtoRes dtoRes = regencyService.insert(dtoReq);
		return new ResponseEntity<InsertRegencyDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRegencyDtoRes> update(@RequestBody @Valid UpdateRegencyDtoReq dtoReq) throws Exception {
		UpdateRegencyDtoRes dtoRes = regencyService.update(dtoReq);
		return new ResponseEntity<UpdateRegencyDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
