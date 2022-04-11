package com.lawencon.community.service;

import com.lawencon.community.dto.position.DeleteByPositionIdDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByPositionIdDtoRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;

public interface PositionService {
	GetAllPositionDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByPositionIdDtoRes findById(String id) throws Exception;
	InsertPositionDtoRes insert(InsertPositionDtoReq data) throws Exception;
	UpdatePositionDtoRes update(UpdatePositionDtoReq data) throws Exception;
	DeleteByPositionIdDtoRes deleteById(String id) throws Exception;
}
