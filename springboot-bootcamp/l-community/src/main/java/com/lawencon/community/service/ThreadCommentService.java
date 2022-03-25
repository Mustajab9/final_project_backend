package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.threadcomment.DeleteByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.GetAllThreadCommentDtoRes;
import com.lawencon.community.dto.threadcomment.GetByThreadCommentIdDtoRes;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoReq;
import com.lawencon.community.dto.threadcomment.InsertThreadCommentDtoRes;
import com.lawencon.community.model.ThreadComment;

public interface ThreadCommentService {
	public GetAllThreadCommentDtoRes findAll() throws Exception;
	public GetByThreadCommentIdDtoRes findById(String id) throws Exception;
	public InsertThreadCommentDtoRes insert(InsertThreadCommentDtoReq data) throws Exception;
	public DeleteByThreadCommentIdDtoRes delete(String id) throws Exception;
	public List<ThreadComment> findByThread(String id) throws Exception;
}
