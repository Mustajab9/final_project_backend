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
	GetAllEnrollEventDtoRes findAll() throws Exception;
	GetByEnrollEventIdDtoRes findById(String id) throws Exception;
	InsertEnrollEventDtoRes insert(String data, MultipartFile file) throws Exception;
	UpdateEnrollEventDtoRes update(UpdateEnrollEventDtoReq data) throws Exception;
	DeleteByEnrollEventIdDtoRes deleteById(String id) throws Exception;
	GetEnrollEventByUserDtoRes findByUser(String id) throws Exception;
}
