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

import com.lawencon.community.dto.pricelistevent.DeleteByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.GetAllPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.GetByPriceListEventIdDtoRes;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.InsertPriceListEventDtoRes;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoReq;
import com.lawencon.community.dto.pricelistevent.UpdatePriceListEventDtoRes;
import com.lawencon.community.service.PriceListEventService;

@RestController
@RequestMapping("price-list-events")
public class PriceListEventController {
	
private PriceListEventService priceListEventService;
	
	@Autowired
	public PriceListEventController(PriceListEventService priceListEventService) {
		this.priceListEventService=priceListEventService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllPriceListEventDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllPriceListEventDtoRes priceListEvents = priceListEventService.findAll(start, max);
		return new ResponseEntity<GetAllPriceListEventDtoRes>(priceListEvents, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByPriceListEventIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPriceListEventIdDtoRes priceListEvent = priceListEventService.findById(id);
		return new ResponseEntity<GetByPriceListEventIdDtoRes>(priceListEvent, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPriceListEventIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPriceListEventIdDtoRes deletePriceListEvent = priceListEventService.deleteById(id);
		return new ResponseEntity<DeleteByPriceListEventIdDtoRes>(deletePriceListEvent, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertPriceListEventDtoRes> save(@RequestBody @Valid InsertPriceListEventDtoReq dtoReq) throws Exception {
		InsertPriceListEventDtoRes dtoRes = priceListEventService.insert(dtoReq);
		return new ResponseEntity<InsertPriceListEventDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePriceListEventDtoRes> update(@RequestBody @Valid UpdatePriceListEventDtoReq dtoReq) throws Exception {
		UpdatePriceListEventDtoRes dtoRes = priceListEventService.update(dtoReq);
		return new ResponseEntity<UpdatePriceListEventDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
