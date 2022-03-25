package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dto.attachment.DeleteByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoDataRes;
import com.lawencon.community.dto.attachment.GetAllAttachmentDtoRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoDataRes;
import com.lawencon.community.dto.attachment.GetByAttachmentIdDtoRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoDataRes;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.service.AttachmentService;

@Service
public class AttachmentServiceImpl extends BaseService implements AttachmentService {
	private AttachmentDao attachmentDao;

	@Autowired
	public AttachmentServiceImpl(AttachmentDao attachmentDao) {
		this.attachmentDao = attachmentDao;
	}
	
	@Override
	public GetAllAttachmentDtoRes findAll() throws Exception {
		GetAllAttachmentDtoRes getAll = new GetAllAttachmentDtoRes();

		List<Attachment> attachments = attachmentDao.findAll();
		List<GetAllAttachmentDtoDataRes> listAttachment = new ArrayList<>();

		for (int i = 0; i < attachments.size(); i++) {
			Attachment attach = attachments.get(i);
			GetAllAttachmentDtoDataRes data = new GetAllAttachmentDtoDataRes();
			
			data.setId(attach.getId());
			data.setAttachmentCode(attach.getAttachmentCode());
			data.setAttachmentContent(attach.getAttachmentContent());
			data.setAttachmentExtension(attach.getAttachmentExtension());
			data.setVersion(attach.getVersion());
			data.setIsActive(attach.getIsActive());

			listAttachment.add(data);
		}

		getAll.setData(listAttachment);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByAttachmentIdDtoRes findById(String id) throws Exception {
		GetByAttachmentIdDtoRes getById = new GetByAttachmentIdDtoRes();

		Attachment attach = attachmentDao.findById(id);
		GetByAttachmentIdDtoDataRes data = new GetByAttachmentIdDtoDataRes();

		data.setId(attach.getId());
		data.setAttachmentCode(attach.getAttachmentCode());
		data.setAttachmentContent(attach.getAttachmentContent());
		data.setAttachmentExtension(attach.getAttachmentExtension());
		data.setVersion(attach.getVersion());
		data.setIsActive(attach.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertAttachmentDtoRes insert(MultipartFile file) throws Exception {
		InsertAttachmentDtoRes insert  = new InsertAttachmentDtoRes();

		try {
			Attachment attach = new Attachment();
			attach.setAttachmentContent(file.getBytes());
			
			String extensionName = fileExtensionName(file);
			attach.setAttachmentExtension(extensionName);
			
			if(getId() != null) {
				attach.setCreatedBy(getId());
			}

			begin();
			Attachment fileInsert = attachmentDao.save(attach);
			commit();

			InsertAttachmentDtoDataRes dataDto = new InsertAttachmentDtoDataRes();
			dataDto.setId(fileInsert.getId());

			insert.setData(dataDto);
			insert.setMsg("Insert Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
	
		return insert;
	}
	
	@Override
	public DeleteByAttachmentIdDtoRes deleteById(String id) throws Exception {
		DeleteByAttachmentIdDtoRes deleteById = new DeleteByAttachmentIdDtoRes();
		
		try {
			begin();
			boolean delete = attachmentDao.deleteById(id);
			commit();
			
			if(delete) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}
		
		
		return deleteById;
	}
}
