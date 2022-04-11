package com.lawencon.community.service;

import com.lawencon.community.dto.industry.DeleteByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;

public interface IndustryService {
	GetAllIndustryDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByIndustryIdDtoRes findById(String id) throws Exception;
	InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception;
	UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception;
	DeleteByIndustryIdDtoRes deleteById(String id) throws Exception;
}
