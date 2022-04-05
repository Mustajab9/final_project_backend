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

import com.lawencon.community.dto.position.DeleteByPositionIdDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByPositionIdDtoRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;
import com.lawencon.community.service.PositionService;

@RestController
@RequestMapping("positions")
public class PositionController {
	
	private PositionService positionService;

	@Autowired
	public PositionController(PositionService positionService) {
		this.positionService=positionService;
	}

	@GetMapping
	public ResponseEntity<GetAllPositionDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllPositionDtoRes positions = positionService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllPositionDtoRes>(positions, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPositionIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPositionIdDtoRes position = positionService.findById(id);
		return new ResponseEntity<GetByPositionIdDtoRes>(position, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPositionIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPositionIdDtoRes deletePosition = positionService.deleteById(id);
		return new ResponseEntity<DeleteByPositionIdDtoRes>(deletePosition, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPositionDtoRes> save(@RequestBody @Valid InsertPositionDtoReq dtoReq) throws Exception {
		InsertPositionDtoRes dtoRes = positionService.insert(dtoReq);
		return new ResponseEntity<InsertPositionDtoRes>(dtoRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdatePositionDtoRes> update(@RequestBody @Valid UpdatePositionDtoReq dtoReq) throws Exception {
		UpdatePositionDtoRes dtoRes = positionService.update(dtoReq);
		return new ResponseEntity<UpdatePositionDtoRes>(dtoRes, HttpStatus.OK);
	}
}
