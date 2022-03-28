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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;
import com.lawencon.community.service.EventService;

@RestController
@RequestMapping("events")
public class EventController {
	private EventService eventService;

	@Autowired
	public void setBookmarkService(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping
	public ResponseEntity<GetAllEventDtoRes> getAll() throws Exception {
		GetAllEventDtoRes result = eventService.findAll();
		return new ResponseEntity<GetAllEventDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByEventIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByEventIdDtoRes data = eventService.findById(id);
		return new ResponseEntity<GetByEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("enroll/{id}")
	public ResponseEntity<GetAllEventDtoRes> getByEnroll(@PathVariable("id") String id) throws Exception {
		GetAllEventDtoRes data = eventService.findEnrollEvent(id);
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("not-enroll/{id}")
	public ResponseEntity<GetAllEventDtoRes> getByNotEnroll(@PathVariable("id") String id) throws Exception {
		GetAllEventDtoRes data = eventService.findNotEnrollEvent(id);
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByEventIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByEventIdDtoRes data = eventService.deleteById(id);
		return new ResponseEntity<DeleteByEventIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEventDtoRes> insertData(@RequestPart(name = "content") String content, @RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventDtoRes insert = eventService.insert(content, file);
		return new ResponseEntity<InsertEventDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEventDtoRes> updateData(@RequestBody @Valid UpdateEventDtoReq data) throws Exception {
		UpdateEventDtoRes update = eventService.update(data);
		return new ResponseEntity<UpdateEventDtoRes>(update, HttpStatus.OK);
	}
}
