package com.lawencon.community.service;

import com.lawencon.community.dto.profilesosmed.DeleteByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.GetAllProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.GetByProfileSosmedIdDtoRes;
import com.lawencon.community.dto.profilesosmed.GetProfileSosmedByUserDtoRes;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.InsertProfileSosmedDtoRes;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoReq;
import com.lawencon.community.dto.profilesosmed.UpdateProfileSosmedDtoRes;

public interface ProfileSosmedService {
	public GetAllProfileSosmedDtoRes findAll() throws Exception;
	public GetByProfileSosmedIdDtoRes findById(String id) throws Exception;
	public InsertProfileSosmedDtoRes insert(InsertProfileSosmedDtoReq data) throws Exception;
	public UpdateProfileSosmedDtoRes update(UpdateProfileSosmedDtoReq data) throws Exception;
	public DeleteByProfileSosmedIdDtoRes deleteById(String id) throws Exception;
	public GetProfileSosmedByUserDtoRes findByUser(String id) throws Exception;
}
