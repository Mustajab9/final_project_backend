package com.lawencon.community.service;

import com.lawencon.community.dto.threadtype.DeleteByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;

public interface ThreadTypeService {
	public GetAllThreadTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public GetByThreadTypeIdDtoRes findById(String id) throws Exception;
	public InsertThreadTypeDtoRes insert(InsertThreadTypeDtoReq data) throws Exception;
	public UpdateThreadTypeDtoRes update(UpdateThreadTypeDtoReq data) throws Exception;
	public DeleteByThreadTypeIdDtoRes deleteById(String id) throws Exception;
}
