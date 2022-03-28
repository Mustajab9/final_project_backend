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

import com.lawencon.community.dto.profilesosmed.DeleteByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.GetAllProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.GetByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoRes;
import com.lawencon.community.service.ProfileSosmedService;

@RestController
@RequestMapping("profile-sosmeds")
public class ProfilSosmedController {

private ProfileSosmedService profileSosmedService;
	
	@Autowired
	public ProfilSosmedController(ProfileSosmedService profileSosmedService) {
		this.profileSosmedService=profileSosmedService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllProfileSosmedDtoRes> getAll() throws Exception {
		GetAllProfileSosmedDtoRes profileSosmeds = profileSosmedService.findAll();
		return new ResponseEntity<GetAllProfileSosmedDtoRes>(profileSosmeds, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByProfileSosmedIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByProfileSosmedIdDtoRes profileSosmed = profileSosmedService.findById(id);
		return new ResponseEntity<GetByProfileSosmedIdDtoRes>(profileSosmed, HttpStatus.OK);
	}
	
//	@GetMapping("code-{code}")
//	public ResponseEntity<GetByRoleCodeDtoRes> getByCode(@PathVariable("code") String code) throws Exception {
//		GetByRoleCodeDtoRes role = profileSosmedService.findIdByCode(code);
//		return new ResponseEntity<GetByRoleCodeDtoRes>(role, HttpStatus.OK);
//	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByProfileSosmedIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByProfileSosmedIdDtoRes deleteProfileSosmed = profileSosmedService.deleteById(id);
		return new ResponseEntity<DeleteByProfileSosmedIdDtoRes>(deleteProfileSosmed, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertProfileSosmedDtoRes> save(@RequestBody @Valid InsertProfileSosmedDtoReq dtoReq) throws Exception {
		InsertProfileSosmedDtoRes dtoRes = profileSosmedService.insert(dtoReq);
		return new ResponseEntity<InsertProfileSosmedDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateProfileSosmedDtoRes> update(@RequestBody @Valid UpdateProfileSosmedDtoReq dtoReq) throws Exception {
		UpdateProfileSosmedDtoRes dtoRes = profileSosmedService.update(dtoReq);
		return new ResponseEntity<UpdateProfileSosmedDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
