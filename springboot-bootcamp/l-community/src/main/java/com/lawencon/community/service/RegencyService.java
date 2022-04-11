package com.lawencon.community.service;

import com.lawencon.community.dto.regency.DeleteByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.GetAllRegencyDtoRes;
import com.lawencon.community.dto.regency.GetByProvinceCodeDtoRes;
import com.lawencon.community.dto.regency.GetByRegencyIdDtoRes;
import com.lawencon.community.dto.regency.InsertRegencyDtoReq;
import com.lawencon.community.dto.regency.InsertRegencyDtoRes;
import com.lawencon.community.dto.regency.UpdateRegencyDtoReq;
import com.lawencon.community.dto.regency.UpdateRegencyDtoRes;

public interface RegencyService {
	GetAllRegencyDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByRegencyIdDtoRes findById(String id) throws Exception;
	InsertRegencyDtoRes insert(InsertRegencyDtoReq data) throws Exception;
	UpdateRegencyDtoRes update(UpdateRegencyDtoReq data) throws Exception;
	DeleteByRegencyIdDtoRes deleteById(String id) throws Exception;
	GetByProvinceCodeDtoRes findByProvince(String code) throws Exception;
}
