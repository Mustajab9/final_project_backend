package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoRes;
import com.lawencon.community.model.EnrollDetail;

public interface EnrollDetailService {
	public GetAllEnrollDetailDtoRes getAll() throws Exception;
	public GetByEnrollDetailIdDtoRes getById(String id) throws Exception;
	public InsertEnrollDetailDtoRes insert(InsertEnrollDetailDtoReq data) throws Exception;
	public List<EnrollDetail> getByEvent(String id) throws Exception;
}
