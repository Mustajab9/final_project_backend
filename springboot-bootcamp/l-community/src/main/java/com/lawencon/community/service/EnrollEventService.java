package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.enrollevent.DeleteByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoRes;
import com.lawencon.community.model.EnrollEvent;

public interface EnrollEventService {
	public GetAllEnrollEventDtoRes getAll() throws Exception;
	public GetByEnrollEventIdDtoRes getById(String id) throws Exception;
	public InsertEnrollEventDtoRes insert(InsertEnrollEventDtoReq data) throws Exception;
	public UpdateEnrollEventDtoRes update(UpdateEnrollEventDtoReq data) throws Exception;
	public DeleteByEnrollEventIdDtoRes deleteById(String id) throws Exception;
	public List<EnrollEvent> getByUser(String id) throws Exception;
}
