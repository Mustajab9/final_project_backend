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

import com.lawencon.community.dto.user.ChangePasswordDtoReq;
import com.lawencon.community.dto.user.DeleteByUserIdDtoRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByUserIdDtoRes;
import com.lawencon.community.dto.user.GetUserByEmailDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;
import com.lawencon.community.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<InsertUserDtoRes> insertData(@RequestBody @Valid InsertUserDtoReq data) throws Exception{
		InsertUserDtoRes insertData = userService.insert(data);
		return new ResponseEntity<InsertUserDtoRes>(insertData, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateUserDtoRes> updateData(@RequestBody @Valid UpdateUserDtoReq data) throws Exception{
		UpdateUserDtoRes updateData = userService.update(data);
		return new ResponseEntity<UpdateUserDtoRes>(updateData, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<GetAllUserDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception{
		GetAllUserDtoRes getAll = userService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllUserDtoRes>(getAll, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByUserIdDtoRes> getById(@PathVariable("id") String id) throws Exception{
		GetByUserIdDtoRes getById = userService.findById(id);
		return new ResponseEntity<GetByUserIdDtoRes>(getById, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByUserIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception{
		DeleteByUserIdDtoRes deleteById = userService.deleteById(id);
		return new ResponseEntity<DeleteByUserIdDtoRes>(deleteById, HttpStatus.OK);
	}
	
	@GetMapping("user-{email}")
	public ResponseEntity<GetUserByEmailDtoDataRes> getByEmail(@PathVariable("email") String email) throws Exception{
		GetUserByEmailDtoDataRes getByEmail = userService.findByUser(email);
		return new ResponseEntity<GetUserByEmailDtoDataRes>(getByEmail, HttpStatus.OK);
	}
	
	@PutMapping("forgot-password")
	public ResponseEntity<UpdateUserDtoRes> forgotPassword(@RequestBody @Valid UpdateUserDtoReq data) throws Exception{
		UpdateUserDtoRes forgotPassword = userService.forgotPassword(data);
		return new ResponseEntity<UpdateUserDtoRes>(forgotPassword, HttpStatus.OK);
	}
	
	@PutMapping("change-password")
	public ResponseEntity<UpdateUserDtoRes> changePassword(@RequestBody @Valid ChangePasswordDtoReq data) throws Exception{
		UpdateUserDtoRes changePassword = userService.changePassword(data);
		return new ResponseEntity<UpdateUserDtoRes>(changePassword, HttpStatus.OK);
	}
}
