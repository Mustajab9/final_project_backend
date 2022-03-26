package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;

public interface EventService {
	public GetAllEventDtoRes findAll() throws Exception;
	public GetByEventIdDtoRes findById(String id) throws Exception;
	public InsertEventDtoRes insert(String data, MultipartFile file) throws Exception;
	public UpdateEventDtoRes update(UpdateEventDtoReq data) throws Exception;
	public DeleteByEventIdDtoRes deleteById(String id) throws Exception;
	public GetAllEventDtoRes findEnrollEvent(String id) throws Exception;
	public GetAllEventDtoRes findNotEnrollEvent(String id) throws Exception;
}
