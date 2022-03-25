package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.thread.DeleteByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;

public interface ThreadService {
	public GetAllThreadDtoRes findAll() throws Exception;
	public GetByThreadIdDtoRes findById(String id) throws Exception;
	public InsertThreadDtoRes insert(String content, MultipartFile[] file) throws Exception;
	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception;
	public DeleteByThreadIdDtoRes deleteById(String id) throws Exception;
	public GetThreadByUserDtoRes findByUser(String id) throws Exception;
	public GetThreadByCategoryDtoRes findByCategory(String id[]) throws Exception;
}
