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

import com.lawencon.community.dto.eventtype.DeleteByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.GetAllEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.GetByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoRes;
import com.lawencon.community.service.EventTypeService;

@RestController
@RequestMapping("event-types")
public class EventTypeController {
	private EventTypeService eventTypeService;

	@Autowired
	public void setEventTypeService(EventTypeService eventTypeService) {
		this.eventTypeService = eventTypeService;
	}

	@GetMapping
	public ResponseEntity<GetAllEventTypeDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllEventTypeDtoRes result = eventTypeService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllEventTypeDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByEventTypeIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByEventTypeIdDtoRes data = eventTypeService.findById(id);
		return new ResponseEntity<GetByEventTypeIdDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByEventTypeIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByEventTypeIdDtoRes data = eventTypeService.deleteById(id);
		return new ResponseEntity<DeleteByEventTypeIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEventTypeDtoRes> insertData(@RequestBody @Valid InsertEventTypeDtoReq data) throws Exception {
		InsertEventTypeDtoRes insert = eventTypeService.insert(data);
		return new ResponseEntity<InsertEventTypeDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEventTypeDtoRes> updateData(@RequestBody @Valid UpdateEventTypeDtoReq data) throws Exception {
		UpdateEventTypeDtoRes update = eventTypeService.update(data);
		return new ResponseEntity<UpdateEventTypeDtoRes>(update, HttpStatus.OK);
	}
}
