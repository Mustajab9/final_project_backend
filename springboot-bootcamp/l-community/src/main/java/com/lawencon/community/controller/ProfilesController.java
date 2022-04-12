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

import com.lawencon.community.dto.profiles.DeleteByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetProfileByUserDtoRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoReq;
import com.lawencon.community.dto.profiles.InsertProfilesDtoRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoReq;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoRes;
import com.lawencon.community.service.ProfilesService;

@RestController
@RequestMapping("profiles")
public class ProfilesController {

private ProfilesService profileService;
	
	@Autowired
	public ProfilesController(ProfilesService profileService) {
		this.profileService=profileService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllProfilesDtoRes> getAll() throws Exception {
		GetAllProfilesDtoRes profiles = profileService.findAll();
		return new ResponseEntity<GetAllProfilesDtoRes>(profiles, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByProfilesIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByProfilesIdDtoRes profile = profileService.findById(id);
		return new ResponseEntity<GetByProfilesIdDtoRes>(profile, HttpStatus.OK);
	}
	
	@GetMapping("user")
	public ResponseEntity<GetProfileByUserDtoRes> getByUserId() throws Exception {
		GetProfileByUserDtoRes profile = profileService.findByUser();
		return new ResponseEntity<GetProfileByUserDtoRes>(profile, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByProfilesIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByProfilesIdDtoRes deleteProfile = profileService.deleteById(id);
		return new ResponseEntity<DeleteByProfilesIdDtoRes>(deleteProfile, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertProfilesDtoRes> save(@RequestBody @Valid InsertProfilesDtoReq dtoReq) throws Exception {
		InsertProfilesDtoRes dtoRes = profileService.insert(dtoReq);
		return new ResponseEntity<InsertProfilesDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateProfilesDtoRes> update(@RequestBody @Valid UpdateProfilesDtoReq dtoReq) throws Exception {
		UpdateProfilesDtoRes dtoRes = profileService.update(dtoReq);
		return new ResponseEntity<UpdateProfilesDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
