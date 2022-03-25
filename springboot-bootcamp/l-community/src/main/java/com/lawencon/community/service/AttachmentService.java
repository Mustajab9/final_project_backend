package com.lawencon.community.service;

import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;

public interface AttachmentService {
	public GetAllAttachmentDtoRes findAll() throws Exception;
	public GetByAttachmentIdDtoRes findById(String id) throws Exception;
	public InsertAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public DeleteByAttachmentIdDtoRes deleteById(String id) throws Exception;
}
