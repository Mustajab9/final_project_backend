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

import com.lawencon.community.dto.province.DeleteByProvinceIdDtoRes;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByProvinceIdDtoRes;
import com.lawencon.community.dto.province.InsertProvinceDtoReq;
import com.lawencon.community.dto.province.InsertProvinceDtoRes;
import com.lawencon.community.dto.province.UpdateProvinceDtoReq;
import com.lawencon.community.dto.province.UpdateProvinceDtoRes;
import com.lawencon.community.service.ProvinceService;

@RestController
@RequestMapping("provinces")
public class ProvinceController {
private ProvinceService provinceService;
	
	@Autowired
	public ProvinceController(ProvinceService provinceService) {
		this.provinceService=provinceService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllProvinceDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllProvinceDtoRes provinces = provinceService.findAll(start, max);
		return new ResponseEntity<GetAllProvinceDtoRes>(provinces, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByProvinceIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByProvinceIdDtoRes province = provinceService.findById(id);
		return new ResponseEntity<GetByProvinceIdDtoRes>(province, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByProvinceIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByProvinceIdDtoRes deleteProvince = provinceService.deleteById(id);
		return new ResponseEntity<DeleteByProvinceIdDtoRes>(deleteProvince, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertProvinceDtoRes> save(@RequestBody @Valid InsertProvinceDtoReq dtoReq) throws Exception {
		InsertProvinceDtoRes dtoRes = provinceService.insert(dtoReq);
		return new ResponseEntity<InsertProvinceDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateProvinceDtoRes> update(@RequestBody @Valid UpdateProvinceDtoReq dtoReq) throws Exception {
		UpdateProvinceDtoRes dtoRes = provinceService.update(dtoReq);
		return new ResponseEntity<UpdateProvinceDtoRes>(dtoRes, HttpStatus.OK);
	}
}
