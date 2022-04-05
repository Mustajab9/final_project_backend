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

import com.lawencon.community.dto.paymentevent.DeleteByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetAllPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.GetByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetPaymentEventByUserDtoRes;
import com.lawencon.community.dto.paymentevent.InsertPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoReq;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoRes;
import com.lawencon.community.service.PaymentEventService;

@RestController
@RequestMapping("payment-events")
public class PaymentEventController {
	private PaymentEventService paymentEventService;

	@Autowired
	public void setPaymentEventService(PaymentEventService paymentEventService) {
		this.paymentEventService = paymentEventService;
	}

	@GetMapping
	public ResponseEntity<GetAllPaymentEventDtoRes> getAll() throws Exception {
		GetAllPaymentEventDtoRes result = paymentEventService.findAll();
		return new ResponseEntity<GetAllPaymentEventDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPaymentEventIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPaymentEventIdDtoRes data = paymentEventService.findById(id);
		return new ResponseEntity<GetByPaymentEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("user/{id}")
	public ResponseEntity<GetPaymentEventByUserDtoRes> getByUser(@PathVariable("id") String id) throws Exception {
		GetPaymentEventByUserDtoRes data = paymentEventService.findByUser(id);
		return new ResponseEntity<GetPaymentEventByUserDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPaymentEventIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPaymentEventIdDtoRes data = paymentEventService.deleteById(id);
		return new ResponseEntity<DeleteByPaymentEventIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPaymentEventDtoRes> insertData(@RequestPart(name = "content") String content, @RequestPart(required = false) MultipartFile file) throws Exception {
		InsertPaymentEventDtoRes insert = paymentEventService.insert(content, file);
		return new ResponseEntity<InsertPaymentEventDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePaymentEventDtoRes> updateData(@RequestBody @Valid UpdatePaymentEventDtoReq data) throws Exception {
		UpdatePaymentEventDtoRes update = paymentEventService.update(data);
		return new ResponseEntity<UpdatePaymentEventDtoRes>(update, HttpStatus.OK);
	}
}
