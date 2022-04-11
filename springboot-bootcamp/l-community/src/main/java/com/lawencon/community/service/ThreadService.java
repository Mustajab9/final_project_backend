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
	GetAllThreadDtoRes findAll() throws Exception;
	GetByThreadIdDtoRes findById(String id) throws Exception;
	InsertThreadDtoRes insert(String content, MultipartFile[] file) throws Exception;
	UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception;
	DeleteByThreadIdDtoRes deleteById(String id) throws Exception;
	GetThreadByUserDtoRes findByUser(String id) throws Exception;
	GetThreadByCategoryDtoRes findByCategory(String id[]) throws Exception;
}
