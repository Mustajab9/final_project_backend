package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoRes;
import com.lawencon.community.model.EnrollDetail;

public interface EnrollDetailService {
	public GetAllEnrollDetailDtoRes findAll() throws Exception;
	public GetByEnrollDetailIdDtoRes findById(String id) throws Exception;
	public InsertEnrollDetailDtoRes insert(InsertEnrollDetailDtoReq data) throws Exception;
	public List<EnrollDetail> findByEvent(String id) throws Exception;
}
