package com.lawencon.community.service;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;

public interface AttachmentService {
	public GetAllAttachmentDtoRes findAll() throws Exception;
	public GetByAttachmentIdDtoRes findById(String id) throws Exception;
	public InsertAttachmentDtoRes insert(MultipartFile file) throws Exception;
	public DeleteByAttachmentIdDtoRes deleteById(String id) throws Exception;
}
