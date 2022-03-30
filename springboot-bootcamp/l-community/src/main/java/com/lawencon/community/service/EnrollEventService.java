package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.enrollevent.DeleteByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetEnrollEventByUserDtoRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoRes;

public interface EnrollEventService {
	public GetAllEnrollEventDtoRes findAll() throws Exception;
	public GetByEnrollEventIdDtoRes findById(String id) throws Exception;
	public InsertEnrollEventDtoRes insert(String data, MultipartFile file) throws Exception;
	public UpdateEnrollEventDtoRes update(UpdateEnrollEventDtoReq data) throws Exception;
	public DeleteByEnrollEventIdDtoRes deleteById(String id) throws Exception;
	public GetEnrollEventByUserDtoRes findByUser(String id) throws Exception;
}
