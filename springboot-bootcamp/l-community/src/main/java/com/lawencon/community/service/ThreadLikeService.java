package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.threadlike.DeleteByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.GetAllThreadLikeDtoRes;
import com.lawencon.community.dto.threadlike.GetByThreadLikeIdDtoRes;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoReq;
import com.lawencon.community.dto.threadlike.InsertThreadLikeDtoRes;
import com.lawencon.community.model.ThreadLike;

public interface ThreadLikeService {
	public GetAllThreadLikeDtoRes getAll() throws Exception;
	public GetByThreadLikeIdDtoRes getById(String id) throws Exception;
	public InsertThreadLikeDtoRes insert(InsertThreadLikeDtoReq data) throws Exception;
	public DeleteByThreadLikeIdDtoRes deleteById(String id) throws Exception;
	public List<ThreadLike> getByUser(String id) throws Exception;
}
