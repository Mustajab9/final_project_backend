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

import com.lawencon.community.dto.role.DeleteByRoleIdDtoRes;
import com.lawencon.community.dto.role.GetAllRoleDtoRes;
import com.lawencon.community.dto.role.GetByRoleCodeDtoRes;
import com.lawencon.community.dto.role.GetByRoleIdDtoRes;
import com.lawencon.community.dto.role.InsertRoleDtoReq;
import com.lawencon.community.dto.role.InsertRoleDtoRes;
import com.lawencon.community.dto.role.UpdateRoleDtoReq;
import com.lawencon.community.dto.role.UpdateRoleDtoRes;
import com.lawencon.community.service.RoleService;

@RestController
@RequestMapping("roles")
public class RoleController {
	private RoleService roleService;
	
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService=roleService;
	}
	
	@GetMapping
	public ResponseEntity<GetAllRoleDtoRes> getAll(@RequestParam int start, @RequestParam int max) throws Exception {
		GetAllRoleDtoRes roles = roleService.findAll(start, max);
		return new ResponseEntity<GetAllRoleDtoRes>(roles, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<GetByRoleIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByRoleIdDtoRes role = roleService.findById(id);
		return new ResponseEntity<GetByRoleIdDtoRes>(role, HttpStatus.OK);
	}
	
	@GetMapping("code-{code}")
	public ResponseEntity<GetByRoleCodeDtoRes> getByCode(@PathVariable("code") String code) throws Exception {
		GetByRoleCodeDtoRes role = roleService.findIdByCode(code);
		return new ResponseEntity<GetByRoleCodeDtoRes>(role, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByRoleIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByRoleIdDtoRes deleteRole = roleService.deleteById(id);
		return new ResponseEntity<DeleteByRoleIdDtoRes>(deleteRole, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<InsertRoleDtoRes> save(@RequestBody @Valid InsertRoleDtoReq dtoReq) throws Exception {
		InsertRoleDtoRes dtoRes = roleService.insert(dtoReq);
		return new ResponseEntity<InsertRoleDtoRes>(dtoRes, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateRoleDtoRes> update(@RequestBody @Valid UpdateRoleDtoReq dtoReq) throws Exception {
		UpdateRoleDtoRes dtoRes = roleService.update(dtoReq);
		return new ResponseEntity<UpdateRoleDtoRes>(dtoRes, HttpStatus.OK);
	}
	
}
