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

import com.lawencon.community.dto.choicevote.GetAllChoiceVoteDtoRes;
import com.lawencon.community.dto.choicevote.GetByChoiceVoteIdDtoRes;
import com.lawencon.community.dto.choicevote.GetByPollingChoiceIdDtoRes;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoReq;
import com.lawencon.community.dto.choicevote.InsertChoiceVoteDtoRes;
import com.lawencon.community.service.ChoiceVoteService;

@RestController
@RequestMapping("choice-votes")
public class ChoiceVoteController {
	private ChoiceVoteService choiceVoteService;

	@Autowired
	public void setBookmarkService(ChoiceVoteService choiceVoteService) {
		this.choiceVoteService = choiceVoteService;
	}

	@GetMapping
	public ResponseEntity<GetAllChoiceVoteDtoRes> getAll() throws Exception {
		GetAllChoiceVoteDtoRes result = choiceVoteService.findAll();
		return new ResponseEntity<GetAllChoiceVoteDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByChoiceVoteIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByChoiceVoteIdDtoRes data = choiceVoteService.findById(id);
		return new ResponseEntity<GetByChoiceVoteIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("choice/{id}")
	public ResponseEntity<GetByPollingChoiceIdDtoRes> getByChoice(@PathVariable("id") String id) throws Exception {
		GetByPollingChoiceIdDtoRes data = choiceVoteService.findByChoice(id);
		return new ResponseEntity<GetByPollingChoiceIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertChoiceVoteDtoRes> insertData(@RequestBody @Valid InsertChoiceVoteDtoReq data) throws Exception {
		InsertChoiceVoteDtoRes insert = choiceVoteService.insert(data);
		return new ResponseEntity<InsertChoiceVoteDtoRes>(insert, HttpStatus.CREATED);
	}
}
