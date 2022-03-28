package com.lawencon.community.service;

import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;

public interface ThreadCommentService {
	public GetAllThreadCommentDtoRes findAll() throws Exception;
	public GetByThreadCommentIdDtoRes findById(String id) throws Exception;
	public InsertThreadCommentDtoRes insert(InsertThreadCommentDtoReq data) throws Exception;
	public DeleteByThreadCommentIdDtoRes deleteById(String id) throws Exception;
	public GetThreadCommentByThreadDtoRes findByThread(String id) throws Exception;
}
