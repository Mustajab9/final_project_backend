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

import com.lawencon.community.dto.enrollevent.DeleteByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetEnrollEventByUserDtoRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoRes;
import com.lawencon.community.service.EnrollEventService;

@RestController
@RequestMapping("enroll-events")
public class EnrollEventController {
	private EnrollEventService enrollEventService;

	@Autowired
	public void setBookmarkService(EnrollEventService enrollEventService) {
		this.enrollEventService = enrollEventService;
	}

	@GetMapping
	public ResponseEntity<GetAllEnrollEventDtoRes> getAll() throws Exception {
		GetAllEnrollEventDtoRes result = enrollEventService.findAll();
		return new ResponseEntity<GetAllEnrollEventDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByEnrollEventIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByEnrollEventIdDtoRes data = enrollEventService.findById(id);
		return new ResponseEntity<GetByEnrollEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetEnrollEventByUserDtoRes> getByUser(@PathVariable("id") String id) throws Exception {
		GetEnrollEventByUserDtoRes data = enrollEventService.findByUser(id);
		return new ResponseEntity<GetEnrollEventByUserDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByEnrollEventIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByEnrollEventIdDtoRes data = enrollEventService.deleteById(id);
		return new ResponseEntity<DeleteByEnrollEventIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEnrollEventDtoRes> insertData(@RequestPart(name = "content") String content, @RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEnrollEventDtoRes insert = enrollEventService.insert(content, file);
		return new ResponseEntity<InsertEnrollEventDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEnrollEventDtoRes> updateData(@RequestBody @Valid UpdateEnrollEventDtoReq data) throws Exception {
		UpdateEnrollEventDtoRes update = enrollEventService.update(data);
		return new ResponseEntity<UpdateEnrollEventDtoRes>(update, HttpStatus.OK);
	}
}
