package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.thread.DeleteByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoReq;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.model.Thread;

public interface ThreadService {
	public GetAllThreadDtoRes getAll() throws Exception;
	public GetByThreadIdDtoRes getById(String id) throws Exception;
	public InsertThreadDtoRes insert(InsertThreadDtoReq data) throws Exception;
	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception;
	public DeleteByThreadIdDtoRes deleteById(String id) throws Exception;
	public List<Thread> getByUser(String id) throws Exception;
	public List<Thread> getByCategory(String id[]) throws Exception;
}
