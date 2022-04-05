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

import com.lawencon.community.dto.paymenteventdetail.GetAllPaymentEventDetailDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetByPaymentEventDetailIdDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetPaymentEventDetailByEventDtoRes;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoReq;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoRes;
import com.lawencon.community.service.PaymentEventDetailService;

@RestController
@RequestMapping("payment-event-details")
public class PaymentEventDetailController {
	private PaymentEventDetailService paymentEventDetailService;

	@Autowired
	public void setPaymentEventDetailService(PaymentEventDetailService paymentEventDetailService) {
		this.paymentEventDetailService = paymentEventDetailService;
	}

	@GetMapping
	public ResponseEntity<GetAllPaymentEventDetailDtoRes> getAll() throws Exception {
		GetAllPaymentEventDetailDtoRes result = paymentEventDetailService.findAll();
		return new ResponseEntity<GetAllPaymentEventDetailDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPaymentEventDetailIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPaymentEventDetailIdDtoRes data = paymentEventDetailService.findById(id);
		return new ResponseEntity<GetByPaymentEventDetailIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("event/{id}")
	public ResponseEntity<GetPaymentEventDetailByEventDtoRes> getByEvent(@PathVariable("id") String id) throws Exception {
		GetPaymentEventDetailByEventDtoRes data = paymentEventDetailService.findByEvent(id);
		return new ResponseEntity<GetPaymentEventDetailByEventDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPaymentEventDetailDtoRes> insertData(@RequestBody @Valid InsertPaymentEventDetailDtoReq data) throws Exception {
		InsertPaymentEventDetailDtoRes insert = paymentEventDetailService.insert(data);
		return new ResponseEntity<InsertPaymentEventDetailDtoRes>(insert, HttpStatus.CREATED);
	}
}
