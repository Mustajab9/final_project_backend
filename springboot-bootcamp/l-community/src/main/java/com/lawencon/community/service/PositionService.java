package com.lawencon.community.service;

import com.lawencon.community.dto.position.DeleteByPositionIdDtoRes;
import com.lawencon.community.dto.position.GetAllPositionDtoRes;
import com.lawencon.community.dto.position.GetByPositionIdDtoRes;
import com.lawencon.community.dto.position.InsertPositionDtoReq;
import com.lawencon.community.dto.position.InsertPositionDtoRes;
import com.lawencon.community.dto.position.UpdatePositionDtoReq;
import com.lawencon.community.dto.position.UpdatePositionDtoRes;

public interface PositionService {
	public GetAllPositionDtoRes getAll() throws Exception;
	public GetByPositionIdDtoRes getById(String id) throws Exception;
	public InsertPositionDtoRes insert(InsertPositionDtoReq data) throws Exception;
	public UpdatePositionDtoRes update(UpdatePositionDtoReq data) throws Exception;
	public DeleteByPositionIdDtoRes deleteById(String id) throws Exception;
}
