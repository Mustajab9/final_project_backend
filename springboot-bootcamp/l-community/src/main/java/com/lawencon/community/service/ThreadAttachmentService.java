package com.lawencon.community.service;

import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetThreadAttachmentByThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;

public interface ThreadAttachmentService {
	public GetAllThreadAttachmentDtoRes findAll() throws Exception;
	public GetByThreadAttachmentIdDtoRes findById(String id) throws Exception;
	public InsertThreadAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public DeleteByThreadAttachmentIdDtoRes delete(String id) throws Exception;
	public GetThreadAttachmentByThreadDtoRes findByThread(String id) throws Exception;
}
