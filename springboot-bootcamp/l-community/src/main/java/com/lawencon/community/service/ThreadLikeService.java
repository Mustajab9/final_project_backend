package com.lawencon.community.service;

import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;

public interface ThreadLikeService {
	public GetAllThreadLikeDtoRes findAll() throws Exception;
	public GetByThreadLikeIdDtoRes findById(String id) throws Exception;
	public InsertThreadLikeDtoRes insert(InsertThreadLikeDtoReq data) throws Exception;
	public DeleteByThreadLikeIdDtoRes deleteById(String id) throws Exception;
	public GetThreadLikeByThreadDtoRes findByThread(String id) throws Exception;
}
