package com.lawencon.community.service;

import com.lawencon.community.dto.profiles.DeleteByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.GetAllProfilesDtoRes;
import com.lawencon.community.dto.profiles.GetByProfilesIdDtoRes;
import com.lawencon.community.dto.profiles.InsertProfilesDtoReq;
import com.lawencon.community.dto.profiles.InsertProfilesDtoRes;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoReq;
import com.lawencon.community.dto.profiles.UpdateProfilesDtoRes;
import com.lawencon.community.model.Profiles;

public interface ProfilesService {
	public GetAllProfilesDtoRes getAll() throws Exception;
	public GetByProfilesIdDtoRes getById(String id) throws Exception;
	public InsertProfilesDtoRes insert(InsertProfilesDtoReq data) throws Exception;
	public UpdateProfilesDtoRes update(UpdateProfilesDtoReq data) throws Exception;
	public DeleteByProfilesIdDtoRes deleteById(String id) throws Exception;
	public Profiles getByUser(String id) throws Exception;
}
