package com.lawencon.community.service;

import com.lawencon.community.dto.province.DeleteByProvinceIdDtoRes;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByProvinceIdDtoRes;
import com.lawencon.community.dto.province.InsertProvinceDtoReq;
import com.lawencon.community.dto.province.InsertProvinceDtoRes;
import com.lawencon.community.dto.province.UpdateProvinceDtoReq;
import com.lawencon.community.dto.province.UpdateProvinceDtoRes;

public interface ProvinceService {
	GetAllProvinceDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByProvinceIdDtoRes findById(String id) throws Exception;
	InsertProvinceDtoRes insert(InsertProvinceDtoReq data) throws Exception;
	UpdateProvinceDtoRes update(UpdateProvinceDtoReq data) throws Exception;
	DeleteByProvinceIdDtoRes deleteById(String id) throws Exception;
}
