package com.lawencon.community.service;

import com.lawencon.community.dto.profiles.DeleteByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetProfileByUserDtoRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoReq;
import com.lawencon.community.dto.profiles.InsertProfilesDtoRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoReq;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoRes;

public interface ProfilesService {
	GetAllProfilesDtoRes findAll() throws Exception;
	GetByProfilesIdDtoRes findById(String id) throws Exception;
	InsertProfilesDtoRes insert(InsertProfilesDtoReq data) throws Exception;
	UpdateProfilesDtoRes update(UpdateProfilesDtoReq data) throws Exception;
	DeleteByProfilesIdDtoRes deleteById(String id) throws Exception;
	GetProfileByUserDtoRes findByUser() throws Exception;
}
