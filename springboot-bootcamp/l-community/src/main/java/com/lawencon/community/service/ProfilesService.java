package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.profiles.DeleteByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetProfileByUserDtoRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoReq;
import com.lawencon.community.dto.profiles.InsertProfilesDtoRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoRes;

public interface ProfilesService {
	GetAllProfilesDtoRes findAll() throws Exception;
	GetByProfilesIdDtoRes findById(String id) throws Exception;
	InsertProfilesDtoRes insert(InsertProfilesDtoReq data) throws Exception;
	UpdateProfilesDtoRes update(String content, MultipartFile file) throws Exception;
	DeleteByProfilesIdDtoRes deleteById(String id) throws Exception;
	GetProfileByUserDtoRes findByUser() throws Exception;
}
