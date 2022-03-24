package com.lawencon.community.service.impl;

import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoReq;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoRes;
import com.lawencon.community.service.AttachmentService;

@Service
public class AttachmentServiceImpl extends BaseServiceImpl implements AttachmentService {
	@Override
	public GetAllAttachmentDtoRes getAll() throws Exception {
		
		return null;
	}
	
	@Override
	public GetByAttachmentIdDtoRes getById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public InsertAttachmentDtoRes insert(InsertAttachmentDtoReq data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public UpdateAttachmentDtoRes update(UpdateAttachmentDtoReq data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public DeleteByAttachmentIdDtoRes deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
