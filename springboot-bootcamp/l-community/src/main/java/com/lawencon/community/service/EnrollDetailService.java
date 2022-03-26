package com.lawencon.community.service;

import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEventIdDtoRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoRes;

public interface EnrollDetailService {
	public GetAllEnrollDetailDtoRes findAll() throws Exception;
	public GetByEnrollDetailIdDtoRes findById(String id) throws Exception;
	public InsertEnrollDetailDtoRes insert(InsertEnrollDetailDtoReq data) throws Exception;
	public GetByEventIdDtoRes findByEvent(String id) throws Exception;
}
