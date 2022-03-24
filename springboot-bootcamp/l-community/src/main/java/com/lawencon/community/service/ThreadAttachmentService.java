package com.lawencon.community.service;

import java.util.List;

import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;
import com.lawencon.community.model.ThreadAttachment;

public interface ThreadAttachmentService {
	public GetAllThreadAttachmentDtoRes getAll() throws Exception;
	public GetByThreadAttachmentIdDtoRes getById(String id) throws Exception;
	public InsertThreadAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public DeleteByThreadAttachmentIdDtoRes delete(String id) throws Exception;
	public List<ThreadAttachment> getByThread(String id);
}
