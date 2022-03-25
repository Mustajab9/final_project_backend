package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;
import com.lawencon.community.model.ThreadAttachment;

public interface ThreadAttachmentService {
	public GetAllThreadAttachmentDtoRes findAll() throws Exception;
	public GetByThreadAttachmentIdDtoRes findById(String id) throws Exception;
	public InsertThreadAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public DeleteByThreadAttachmentIdDtoRes delete(String id) throws Exception;
	public List<ThreadAttachment> findByThread(String id) throws Exception;
}
