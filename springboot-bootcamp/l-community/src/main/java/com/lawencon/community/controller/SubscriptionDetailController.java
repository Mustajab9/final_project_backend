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

import com.lawencon.community.dto.subscriptiondetail.GetAllSubscriptionDetailDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetBySubscriptionDetailIdDtoRes;
import com.lawencon.community.dto.subscriptiondetail.GetSubscriptionDetailBySubscriptionDtoRes;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoReq;
import com.lawencon.community.dto.subscriptiondetail.InsertSubscriptionDetailDtoRes;
import com.lawencon.community.service.SubscriptionDetailService;

@RestController
@RequestMapping("subscription-details")
public class SubscriptionDetailController {
	private SubscriptionDetailService detailService;

	@Autowired
	private SubscriptionDetailController(SubscriptionDetailService detailService) {
		this.detailService = detailService;
	}

	@PostMapping
	public ResponseEntity<InsertSubscriptionDetailDtoRes> insertData(@RequestBody @Valid InsertSubscriptionDetailDtoReq data) throws Exception {
		InsertSubscriptionDetailDtoRes insertData = detailService.insert(data);
		return new ResponseEntity<InsertSubscriptionDetailDtoRes>(insertData, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<GetAllSubscriptionDetailDtoRes> getAll() throws Exception {
		GetAllSubscriptionDetailDtoRes getAll = detailService.findAll();
		return new ResponseEntity<GetAllSubscriptionDetailDtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetBySubscriptionDetailIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetBySubscriptionDetailIdDtoRes getById = detailService.findById(id);
		return new ResponseEntity<GetBySubscriptionDetailIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@GetMapping("subscription/{id}")
	public ResponseEntity<GetSubscriptionDetailBySubscriptionDtoRes> getBySubscription(@PathVariable("id") String id) throws Exception {
		GetSubscriptionDetailBySubscriptionDtoRes getBySubscription = detailService.findBySubscription(id);
		return new ResponseEntity<GetSubscriptionDetailBySubscriptionDtoRes>(getBySubscription, HttpStatus.OK);
	}
}
