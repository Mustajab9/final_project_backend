package com.lawencon.community.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lawencon.community.dto.user.ChangePasswordDtoReq;
import com.lawencon.community.dto.user.DeleteByUserIdDtoRes;
import com.lawencon.community.dto.user.GetAllUserDtoRes;
import com.lawencon.community.dto.user.GetByUserIdDtoRes;
import com.lawencon.community.dto.user.GetUserByEmailDtoDataRes;
import com.lawencon.community.dto.user.InsertUserDtoReq;
import com.lawencon.community.dto.user.InsertUserDtoRes;
import com.lawencon.community.dto.user.UpdateUserDtoReq;
import com.lawencon.community.dto.user.UpdateUserDtoRes;

public interface UserService extends UserDetailsService {
	GetAllUserDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByUserIdDtoRes findById(String id) throws Exception;
	InsertUserDtoRes insert(InsertUserDtoReq data) throws Exception;
	UpdateUserDtoRes update(UpdateUserDtoReq data) throws Exception;
	UpdateUserDtoRes forgotPassword(UpdateUserDtoReq data) throws Exception;
	UpdateUserDtoRes changePassword(ChangePasswordDtoReq data) throws Exception;
	DeleteByUserIdDtoRes deleteById(String id) throws Exception;
	GetUserByEmailDtoDataRes findByUser(String email) throws Exception;
}
