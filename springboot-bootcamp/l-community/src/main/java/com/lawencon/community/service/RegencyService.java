package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.regency.DeleteByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.GetAllRegencyDtoRes;
import com.lawencon.community.dto.regency.GetByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.InsertRegencyDtoReq;
import com.lawencon.community.dto.regency.InsertRegencyDtoRes;
import com.lawencon.community.dto.regency.UpdateRegencyDtoReq;
import com.lawencon.community.dto.regency.UpdateRegencyDtoRes;
import com.lawencon.community.model.Regency;

public interface RegencyService {
	public GetAllRegencyDtoRes findAll(int startPage, int maxPage) throws Exception;
	public GetByRegencyIdDtoRes findById(String id) throws Exception;
	public InsertRegencyDtoRes insert(InsertRegencyDtoReq data) throws Exception;
	public UpdateRegencyDtoRes update(UpdateRegencyDtoReq data) throws Exception;
	public DeleteByRegencyIdDtoRes deleteById(String id) throws Exception;
	public List<Regency> findByProvince(String code) throws Exception;
}
