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

import com.lawencon.community.dto.socialmedia.DeleteBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.GetAllSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.GetBySocialMediaIdDtoRes;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.InsertSocialMediaDtoRes;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoReq;
import com.lawencon.community.dto.socialmedia.UpdateSocialMediaDtoRes;
import com.lawencon.community.service.SocialMediaService;

@RestController
@RequestMapping("social-medias")
public class SocialMediaController {
	private SocialMediaService socialMediaService;

	@Autowired
	private SocialMediaController(SocialMediaService socialMediaService) {
		this.socialMediaService = socialMediaService;
	}

	@PostMapping
	public ResponseEntity<InsertSocialMediaDtoRes> insertData(@RequestBody @Valid InsertSocialMediaDtoReq data) throws Exception {
		InsertSocialMediaDtoRes insertData = socialMediaService.insert(data);
		return new ResponseEntity<InsertSocialMediaDtoRes>(insertData, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<UpdateSocialMediaDtoRes> updateData(@RequestBody @Valid UpdateSocialMediaDtoReq data) throws Exception {
		UpdateSocialMediaDtoRes updateData = socialMediaService.update(data);
		return new ResponseEntity<UpdateSocialMediaDtoRes>(updateData, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<GetAllSocialMediaDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllSocialMediaDtoRes getAll = socialMediaService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllSocialMediaDtoRes>(getAll, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<GetBySocialMediaIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetBySocialMediaIdDtoRes getById = socialMediaService.findById(id);
		return new ResponseEntity<GetBySocialMediaIdDtoRes>(getById, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteBySocialMediaIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteBySocialMediaIdDtoRes deleteById = socialMediaService.deleteById(id);
		return new ResponseEntity<DeleteBySocialMediaIdDtoRes>(deleteById, HttpStatus.OK);
	}
}
