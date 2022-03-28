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

import com.lawencon.community.dto.paymentmethod.DeleteByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.GetAllPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.GetByPaymentMethodIdDtoRes;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.InsertPaymentMethodDtoRes;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoReq;
import com.lawencon.community.dto.paymentmethod.UpdatePaymentMethodDtoRes;
import com.lawencon.community.service.PaymentMethodService;

@RestController
@RequestMapping("payment-methods")
public class PaymentMethodController {
	private PaymentMethodService paymentMethodService;

	@Autowired
	public void setBookmarkService(PaymentMethodService paymentMethodService) {
		this.paymentMethodService = paymentMethodService;
	}

	@GetMapping
	public ResponseEntity<GetAllPaymentMethodDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllPaymentMethodDtoRes result = paymentMethodService.findAll(start, max);
		return new ResponseEntity<GetAllPaymentMethodDtoRes>(result, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetByPaymentMethodIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPaymentMethodIdDtoRes data = paymentMethodService.findById(id);
		return new ResponseEntity<GetByPaymentMethodIdDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPaymentMethodIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPaymentMethodIdDtoRes data = paymentMethodService.deleteById(id);
		return new ResponseEntity<DeleteByPaymentMethodIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertPaymentMethodDtoRes> insertData(@RequestBody @Valid InsertPaymentMethodDtoReq data) throws Exception {
		InsertPaymentMethodDtoRes insert = paymentMethodService.insert(data);
		return new ResponseEntity<InsertPaymentMethodDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePaymentMethodDtoRes> updateData(@RequestBody @Valid UpdatePaymentMethodDtoReq data) throws Exception {
		UpdatePaymentMethodDtoRes update = paymentMethodService.update(data);
		return new ResponseEntity<UpdatePaymentMethodDtoRes>(update, HttpStatus.OK);
	}
}
