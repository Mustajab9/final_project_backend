package com.lawencon.community.service;

import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetThreadAttachmentByThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;

public interface ThreadAttachmentService {
	GetAllThreadAttachmentDtoRes findAll() throws Exception;
	GetByThreadAttachmentIdDtoRes findById(String id) throws Exception;
	InsertThreadAttachmentDtoRes insert(InsertThreadAttachmentDtoReq data) throws Exception;
	DeleteByThreadAttachmentIdDtoRes deleteById(String id) throws Exception;
	GetThreadAttachmentByThreadDtoRes findByThread(String id) throws Exception;
}
