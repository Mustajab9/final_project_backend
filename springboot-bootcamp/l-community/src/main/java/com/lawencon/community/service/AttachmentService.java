package com.lawencon.community.service;

import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoReq;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoRes;

public interface AttachmentService {
	public GetAllAttachmentDtoRes getAll() throws Exception;
	public GetByAttachmentIdDtoRes getById(String id) throws Exception;
	public InsertAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception;
	public UpdateAttachmentDtoRes update(UpdateAttachmentDtoReq data) throws Exception;
	public DeleteByAttachmentIdDtoRes deleteById(String id) throws Exception;
}
