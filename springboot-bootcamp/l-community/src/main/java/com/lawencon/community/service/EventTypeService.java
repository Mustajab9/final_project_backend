package com.lawencon.community.service;

import com.lawencon.community.dto.eventtype.DeleteByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.GetAllEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.GetByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoRes;

public interface EventTypeService {
	GetAllEventTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByEventTypeIdDtoRes findById(String id) throws Exception;
	InsertEventTypeDtoRes insert(InsertEventTypeDtoReq data) throws Exception;
	UpdateEventTypeDtoRes update(UpdateEventTypeDtoReq data) throws Exception;
	DeleteByEventTypeIdDtoRes deleteById(String id) throws Exception;
}
