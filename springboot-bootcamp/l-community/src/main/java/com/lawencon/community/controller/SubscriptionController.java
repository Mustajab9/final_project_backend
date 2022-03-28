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

import com.lawencon.community.dto.subscription.DeleteBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.GetAllSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.GetBySubscriptionIdDtoRes;
import com.lawencon.community.dto.subscription.GetSubscriptionByUserDtoRes;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.InsertSubscriptionDtoRes;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoReq;
import com.lawencon.community.dto.subscription.UpdateSubscriptionDtoRes;
import com.lawencon.community.service.SubscriptionService;

@RestController
@RequestMapping("subscriptions")
public class SubscriptionController {
	private SubscriptionService subscriptionService;
	
	@Autowired
	private SubscriptionController(SubscriptionService subscriptionService) {
		this.subscriptionService = subscriptionService;
	}
	
	@PostMapping
	public ResponseEntity<InsertSubscriptionDtoRes> insertData(@RequestBody @Valid InsertSubscriptionDtoReq data) throws Exception{
		InsertSubscriptionDtoRes insertData = subscriptionService.insert(data);
		return new ResponseEntity<InsertSubscriptionDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateSubscriptionDtoRes> updateData(@RequestBody @Valid UpdateSubscriptionDtoReq data) throws Exception{
		UpdateSubscriptionDtoRes updateData = subscriptionService.update(data);
		return new ResponseEntity<UpdateSubscriptionDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllSubscriptionDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception{
		GetAllSubscriptionDtoRes getAll = subscriptionService.findAll(start, max);
		return new ResponseEntity<GetAllSubscriptionDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetBySubscriptionIdDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetBySubscriptionIdDtoRes getById = subscriptionService.findById(id);
		return new ResponseEntity<GetBySubscriptionIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteBySubscriptionIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception{
		DeleteBySubscriptionIdDtoRes deleteById = subscriptionService.deleteById(id);
		return new ResponseEntity<DeleteBySubscriptionIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetSubscriptionByUserDtoRes> getByUser(@PathVariable("id") String id) throws Exception{
		GetSubscriptionByUserDtoRes getByUser = subscriptionService.findByUser(id);
		return new ResponseEntity<GetSubscriptionByUserDtoRes>(getByUser, HttpStatus.OK);
	}
}
