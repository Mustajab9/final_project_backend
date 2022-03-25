package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.threadcategory.DeleteByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetAllThreadCategoryDtoRes;
import com.lawencon.community.dto.threadcategory.GetByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoRes;
import com.lawencon.community.model.ThreadComment;

public interface ThreadCategoryService {
	public GetAllThreadCategoryDtoRes findAll() throws Exception;
	public GetByThreadCategoryIdDtoRes findById(String id) throws Exception;
	public InsertThreadCategoryDtoRes insert(InsertThreadCategoryDtoReq data) throws Exception;
	public DeleteByThreadCategoryIdDtoRes delete(String id) throws Exception;
	public List<ThreadComment> findByThread(String id) throws Exception;
}
