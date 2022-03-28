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

import com.lawencon.community.dto.pricelistmember.DeleteByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.GetAllPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.GetByPriceListMemberIdDtoRes;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.InsertPriceListMemberDtoRes;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoReq;
import com.lawencon.community.dto.pricelistmember.UpdatePriceListMemberDtoRes;
import com.lawencon.community.service.PriceListMemberService;

@RestController
@RequestMapping("price-list-members")
public class PriceListMemberController {

private PriceListMemberService priceListMemberService;
	
	@Autowired
	public PriceListMemberController(PriceListMemberService priceListMemberService) {
		this.priceListMemberService=priceListMemberService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllPriceListMemberDtoRes> getAll(@RequestParam int startPage, @RequestParam int maxPage) throws Exception {
		GetAllPriceListMemberDtoRes priceListMembers = priceListMemberService.findAll(startPage, maxPage);
		return new ResponseEntity<GetAllPriceListMemberDtoRes>(priceListMembers, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByPriceListMemberIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByPriceListMemberIdDtoRes priceListMember = priceListMemberService.findById(id);
		return new ResponseEntity<GetByPriceListMemberIdDtoRes>(priceListMember, HttpStatus.OK);
	}
	
//	@GetMapping("code-{code}")
//	public ResponseEntity<GetByRoleCodeDtoRes> getByCode(@PathVariable("code") String code) throws Exception {
//		GetByRoleCodeDtoRes role = priceListMemberService.findIdByCode(code);
//		return new ResponseEntity<GetByRoleCodeDtoRes>(role, HttpStatus.OK);
//	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByPriceListMemberIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByPriceListMemberIdDtoRes deletePriceListMember = priceListMemberService.deleteById(id);
		return new ResponseEntity<DeleteByPriceListMemberIdDtoRes>(deletePriceListMember, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertPriceListMemberDtoRes> save(@RequestBody @Valid InsertPriceListMemberDtoReq dtoReq) throws Exception {
		InsertPriceListMemberDtoRes dtoRes = priceListMemberService.insert(dtoReq);
		return new ResponseEntity<InsertPriceListMemberDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdatePriceListMemberDtoRes> update(@RequestBody @Valid UpdatePriceListMemberDtoReq dtoReq) throws Exception {
		UpdatePriceListMemberDtoRes dtoRes = priceListMemberService.update(dtoReq);
		return new ResponseEntity<UpdatePriceListMemberDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
