package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoReq;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoReq;
import com.lawencon.community.dto.attachment.UpdateAttachmentDtoRes;
import com.lawencon.community.service.AttachmentService;

@Service
public class AttachmentServiceImpl extends BaseService implements AttachmentService {
	private AttachmentDao attachmentDao;

	@Autowired
	public AttachmentServiceImpl(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
}
