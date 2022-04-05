package com.lawencon.community.service;

import com.lawencon.community.dto.eventtype.DeleteByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.GetAllEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.GetByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoRes;

public interface EventTypeService {
	public GetAllEventTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public GetByEventTypeIdDtoRes findById(String id) throws Exception;
	public InsertEventTypeDtoRes insert(InsertEventTypeDtoReq data) throws Exception;
	public UpdateEventTypeDtoRes update(UpdateEventTypeDtoReq data) throws Exception;
	public DeleteByEventTypeIdDtoRes deleteById(String id) throws Exception;
}
