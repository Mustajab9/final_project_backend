package com.lawencon.community.service;

import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetCountCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.GetThreadCommentByThreadDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;

public interface ThreadCommentService {
	GetAllThreadCommentDtoRes findAll() throws Exception;
	GetByThreadCommentIdDtoRes findById(String id) throws Exception;
	InsertThreadCommentDtoRes insert(InsertThreadCommentDtoReq data) throws Exception;
	DeleteByThreadCommentIdDtoRes deleteById(String id) throws Exception;
	GetThreadCommentByThreadDtoRes findByThread(String id) throws Exception;
	GetCountCommentByThreadDtoRes countByThread(String id) throws Exception;
}
