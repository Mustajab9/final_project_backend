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

import com.lawencon.community.dto.pollingchoice.DeleteByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetAllPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.pollingchoice.GetByPollingIdDtoRes;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.InsertPollingChoiceDtoRes;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoReq;
import com.lawencon.community.dto.pollingchoice.UpdatePollingChoiceDtoRes;
import com.lawencon.community.service.PollingChoiceService;

@RestController
@RequestMapping("polling-choices")
public class PollingChoiceController {

	private PollingChoiceService pollingChoiceService;

	@Autowired
	public PollingChoiceController(PollingChoiceService pollingChoiceService) {
		this.pollingChoiceService=pollingChoiceService;
	}

	@GetMapping
	public ResponseEntity<GetAllPollingChoiceDtoRes> getAll() throws Exception {
		GetAllPollingChoiceDtoRes pollingChoices = pollingChoiceService.findAll();
		return new ResponseEntity<GetAllPollingChoiceDtoRes>(pollingChoices, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPollingChoiceIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPollingChoiceIdDtoRes pollingChoice = pollingChoiceService.findById(id);
		return new ResponseEntity<GetByPollingChoiceIdDtoRes>(pollingChoice, HttpStatus.OK);
	}

	@GetMapping("pollingId-{pollingId}")
	public ResponseEntity<GetByPollingIdDtoRes> getByCode(@PathVariable("pollingId") String pollingId) throws Exception {
		GetByPollingIdDtoRes getByPollingId = pollingChoiceService.findByPolling(pollingId);
		return new ResponseEntity<GetByPollingIdDtoRes>(getByPollingId, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPollingChoiceIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPollingChoiceIdDtoRes deletePollingChoice = pollingChoiceService.deleteById(id);
		return new ResponseEntity<DeleteByPollingChoiceIdDtoRes>(deletePollingChoice, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPollingChoiceDtoRes> save(@RequestBody @Valid InsertPollingChoiceDtoReq dtoReq) throws Exception {
		InsertPollingChoiceDtoRes dtoRes = pollingChoiceService.insert(dtoReq);
		return new ResponseEntity<InsertPollingChoiceDtoRes>(dtoRes, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdatePollingChoiceDtoRes> update(@RequestBody @Valid UpdatePollingChoiceDtoReq dtoReq) throws Exception {
		UpdatePollingChoiceDtoRes dtoRes = pollingChoiceService.update(dtoReq);
		return new ResponseEntity<UpdatePollingChoiceDtoRes>(dtoRes, HttpStatus.OK);
	}

}
