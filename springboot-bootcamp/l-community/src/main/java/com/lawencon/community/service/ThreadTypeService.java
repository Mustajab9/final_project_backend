package com.lawencon.community.service;

import com.lawencon.community.dto.threadtype.DeleteByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.GetAllThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.GetByThreadTypeIdDtoRes;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.InsertThreadTypeDtoRes;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoReq;
import com.lawencon.community.dto.threadtype.UpdateThreadTypeDtoRes;

public interface ThreadTypeService {
	GetAllThreadTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByThreadTypeIdDtoRes findById(String id) throws Exception;
	InsertThreadTypeDtoRes insert(InsertThreadTypeDtoReq data) throws Exception;
	UpdateThreadTypeDtoRes update(UpdateThreadTypeDtoReq data) throws Exception;
	DeleteByThreadTypeIdDtoRes deleteById(String id) throws Exception;
}
