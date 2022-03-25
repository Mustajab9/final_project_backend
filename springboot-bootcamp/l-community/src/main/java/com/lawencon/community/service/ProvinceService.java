package com.lawencon.community.service;

import com.lawencon.community.dto.province.DeleteByProvinceIdDtoRes;
import com.lawencon.community.dto.province.GetAllProvinceDtoRes;
import com.lawencon.community.dto.province.GetByProvinceIdDtoRes;
import com.lawencon.community.dto.province.InsertProvinceDtoReq;
import com.lawencon.community.dto.province.InsertProvinceDtoRes;
import com.lawencon.community.dto.province.UpdateProvinceDtoReq;
import com.lawencon.community.dto.province.UpdateProvinceDtoRes;

public interface ProvinceService {
	public GetAllProvinceDtoRes findAll() throws Exception;
	public GetByProvinceIdDtoRes findById(String id) throws Exception;
	public InsertProvinceDtoRes insert(InsertProvinceDtoReq data) throws Exception;
	public UpdateProvinceDtoRes update(UpdateProvinceDtoReq data) throws Exception;
	public DeleteByProvinceIdDtoRes deleteById(String id) throws Exception;
}
