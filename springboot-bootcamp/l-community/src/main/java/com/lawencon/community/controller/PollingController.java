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

import com.lawencon.community.dto.polling.DeleteByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetAllPollingDtoRes;
import com.lawencon.community.dto.polling.GetByPollingIdDtoRes;
import com.lawencon.community.dto.polling.GetByThreadIdDtoRes;
import com.lawencon.community.dto.polling.InsertPollingDtoReq;
import com.lawencon.community.dto.polling.InsertPollingDtoRes;
import com.lawencon.community.service.PollingService;

@RestController
@RequestMapping("pollings")
public class PollingController {

	private PollingService pollingService;

	@Autowired
	public PollingController(PollingService pollingService) {
		this.pollingService=pollingService;
	}

	@GetMapping
	public ResponseEntity<GetAllPollingDtoRes> getAll() throws Exception {
		GetAllPollingDtoRes pollings = pollingService.findAll();
		return new ResponseEntity<GetAllPollingDtoRes>(pollings, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPollingIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPollingIdDtoRes polling = pollingService.findById(id);
		return new ResponseEntity<GetByPollingIdDtoRes>(polling, HttpStatus.OK);
	}

//	@GetMapping("threadId-{threadId}")
//	public ResponseEntity<GetByThreadIdDtoRes> getByCode(@PathVariable("threadId") String threadId) throws Exception {
//		GetByThreadIdDtoRes getByThreadId = pollingService.findByThread(threadId);
//		return new ResponseEntity<GetByThreadIdDtoRes>(getByThreadId, HttpStatus.OK);
//	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPollingIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPollingIdDtoRes deletePolling = pollingService.deleteById(id);
		return new ResponseEntity<DeleteByPollingIdDtoRes>(deletePolling, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPollingDtoRes> save(@RequestBody @Valid InsertPollingDtoReq dtoReq) throws Exception {
		InsertPollingDtoRes dtoRes = pollingService.insert(dtoReq);
		return new ResponseEntity<InsertPollingDtoRes>(dtoRes, HttpStatus.CREATED);
	}

}
