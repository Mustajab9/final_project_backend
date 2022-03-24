package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.InsertEventDtoReq;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;
import com.lawencon.community.model.Event;

public interface EventService {
	public GetAllEventDtoRes getAll() throws Exception;
	public GetByEventIdDtoRes getById(String id) throws Exception;
	public InsertEventDtoRes insert(InsertEventDtoReq data) throws Exception;
	public UpdateEventDtoRes update(UpdateEventDtoReq data) throws Exception;
	public DeleteByEventIdDtoRes deleteById(String id) throws Exception;
	public List<Event> getEnrollEvent(String id) throws Exception;
	public List<Event> getNotEnrollEvent(String id) throws Exception;
}
