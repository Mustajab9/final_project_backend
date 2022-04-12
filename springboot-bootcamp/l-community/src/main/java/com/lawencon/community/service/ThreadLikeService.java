package com.lawencon.community.service;

import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetByUserIdDtoRes;
import com.lawencon.community.dto.threadlike.GetThreadLikeByThreadDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;

public interface ThreadLikeService {
	GetAllThreadLikeDtoRes findAll() throws Exception;
	GetByThreadLikeIdDtoRes findById(String id) throws Exception;
	InsertThreadLikeDtoRes insert(InsertThreadLikeDtoReq data) throws Exception;
	DeleteByThreadLikeIdDtoRes deleteById(String id) throws Exception;
	GetThreadLikeByThreadDtoRes findByThread(String id) throws Exception;
	GetThreadLikeByThreadDtoRes findByThreadAndUser(String userId, String threadId) throws Exception;
	GetByUserIdDtoRes findByUser() throws Exception;
}
