package com.lawencon.community.service;

import com.lawencon.community.dto.threadcategory.DeleteByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetAllThreadCategoryDtoRes;
import com.lawencon.community.dto.threadcategory.GetByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetThreadCategoryByThreadDtoRes;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoRes;

public interface ThreadCategoryService {
	GetAllThreadCategoryDtoRes findAll() throws Exception;
	GetByThreadCategoryIdDtoRes findById(String id) throws Exception;
	InsertThreadCategoryDtoRes insert(InsertThreadCategoryDtoReq data) throws Exception;
	DeleteByThreadCategoryIdDtoRes deleteById(String id) throws Exception;
	GetThreadCategoryByThreadDtoRes findByThread(String id) throws Exception;
}
