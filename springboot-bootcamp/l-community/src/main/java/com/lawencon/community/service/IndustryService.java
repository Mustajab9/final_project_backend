package com.lawencon.community.service;

import com.lawencon.community.dto.industry.DeleteByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.GetAllIndustryDtoRes;
import com.lawencon.community.dto.industry.GetByIndustryIdDtoRes;
import com.lawencon.community.dto.industry.InsertIndustryDtoReq;
import com.lawencon.community.dto.industry.InsertIndustryDtoRes;
import com.lawencon.community.dto.industry.UpdateIndustryDtoReq;
import com.lawencon.community.dto.industry.UpdateIndustryDtoRes;

public interface IndustryService {
	public GetAllIndustryDtoRes findAll(int startPage, int maxPage) throws Exception;
	public GetByIndustryIdDtoRes findById(String id) throws Exception;
	public InsertIndustryDtoRes insert(InsertIndustryDtoReq data) throws Exception;
	public UpdateIndustryDtoRes update(UpdateIndustryDtoReq data) throws Exception;
	public DeleteByIndustryIdDtoRes deleteById(String id) throws Exception;
}
