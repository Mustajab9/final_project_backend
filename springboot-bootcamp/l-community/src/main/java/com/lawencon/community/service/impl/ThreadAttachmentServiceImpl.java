package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.threadattachment.DeleteByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoDataRes;
import com.lawencon.community.dto.threadattachment.GetAllThreadAttachmentDtoRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoDataRes;
import com.lawencon.community.dto.threadattachment.GetByThreadAttachmentIdDtoRes;
import com.lawencon.community.dto.threadattachment.GetThreadAttachmentByThreadDtoDataRes;
import com.lawencon.community.dto.threadattachment.GetThreadAttachmentByThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoDataRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoReq;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.service.ThreadAttachmentService;

@Service
public class ThreadAttachmentServiceImpl extends BaseService implements ThreadAttachmentService {
	private ThreadAttachmentDao threadAttachmentDao;
	private AttachmentDao attachmentDao;
	private ThreadDao threadDao;

	@Autowired
	public ThreadAttachmentServiceImpl(ThreadAttachmentDao threadAttachmentDao, AttachmentDao attachmentDao, ThreadDao threadDao) {
		this.threadAttachmentDao = threadAttachmentDao;
		this.attachmentDao = attachmentDao;
		this.threadDao = threadDao;
	}
	
	@Override
	public GetAllThreadAttachmentDtoRes findAll() throws Exception {
		GetAllThreadAttachmentDtoRes getAll = new GetAllThreadAttachmentDtoRes();

		List<ThreadAttachment> threadAttachments = threadAttachmentDao.findAll();
		List<GetAllThreadAttachmentDtoDataRes> listThreadAttachment = new ArrayList<>();

		for (int i = 0; i < threadAttachments.size(); i++) {
			ThreadAttachment threadAttachment = threadAttachments.get(i);
			GetAllThreadAttachmentDtoDataRes data = new GetAllThreadAttachmentDtoDataRes();

			data.setId(threadAttachment.getId());
			data.setThreadId(threadAttachment.getThreadId().getId());
			data.setThreadTitle(threadAttachment.getThreadId().getThreadTitle());
			data.setThreadContent(threadAttachment.getThreadId().getThreadContent());
			data.setAttachmentId(threadAttachment.getAttachmentId().getId());
			data.setAttachmentExtension(threadAttachment.getAttachmentId().getAttachmentExtension());
			data.setVersion(threadAttachment.getVersion());
			data.setIsActive(threadAttachment.getIsActive());

			listThreadAttachment.add(data);
		}

		getAll.setData(listThreadAttachment);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadAttachmentIdDtoRes findById(String id) throws Exception {
		GetByThreadAttachmentIdDtoRes getById = new GetByThreadAttachmentIdDtoRes();

		ThreadAttachment threadAttachment = threadAttachmentDao.findById(id);
		GetByThreadAttachmentIdDtoDataRes data = new GetByThreadAttachmentIdDtoDataRes();

		data.setId(threadAttachment.getId());
		data.setThreadId(threadAttachment.getThreadId().getId());
		data.setThreadTitle(threadAttachment.getThreadId().getThreadTitle());
		data.setThreadContent(threadAttachment.getThreadId().getThreadContent());
		data.setAttachmentId(threadAttachment.getAttachmentId().getId());
		data.setAttachmentExtension(threadAttachment.getAttachmentId().getAttachmentExtension());
		data.setVersion(threadAttachment.getVersion());
		data.setIsActive(threadAttachment.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadAttachmentDtoRes insert(InsertThreadAttachmentDtoReq data) throws Exception {
		InsertThreadAttachmentDtoRes insert = new InsertThreadAttachmentDtoRes();

		try {
			ThreadAttachment threadAttachment = new ThreadAttachment();			
			
			Thread thread = threadDao.findById(data.getThreadId());
			threadAttachment.setThreadId(thread);
			
			Attachment attachment = attachmentDao.findById(data.getAttachmentId());
			threadAttachment.setAttachmentId(attachment);
			threadAttachment.setCreatedBy(getId());

			ThreadAttachment threadAttachmentInsert = threadAttachmentDao.save(threadAttachment);

			InsertThreadAttachmentDtoDataRes dataDto = new InsertThreadAttachmentDtoDataRes();
			dataDto.setId(threadAttachmentInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public DeleteByThreadAttachmentIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadAttachmentIdDtoRes deleteById = new DeleteByThreadAttachmentIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadAttachmentDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetThreadAttachmentByThreadDtoRes findByThread(String id) throws Exception {
		GetThreadAttachmentByThreadDtoRes getByThread = new GetThreadAttachmentByThreadDtoRes();

		List<ThreadAttachment> threadAttachments = threadAttachmentDao.findByThread(id);
		List<GetThreadAttachmentByThreadDtoDataRes> listThreadAttachment = new ArrayList<>();

		for (int i = 0; i < threadAttachments.size(); i++) {
			ThreadAttachment threadAttachment = threadAttachments.get(i);
			GetThreadAttachmentByThreadDtoDataRes data = new GetThreadAttachmentByThreadDtoDataRes();

			data.setId(threadAttachment.getId());
			data.setThreadId(threadAttachment.getThreadId().getId());
			data.setThreadCode(threadAttachment.getThreadId().getThreadCode());
			data.setThreadTitle(threadAttachment.getThreadId().getThreadTitle());
			data.setThreadContent(threadAttachment.getThreadId().getThreadContent());
			data.setIsPremium(threadAttachment.getThreadId().getIsPremium());
			data.setTypeId(threadAttachment.getThreadId().getTypeId().getId());
			data.setTypeCode(threadAttachment.getThreadId().getTypeId().getTypeCode());
			data.setTypeName(threadAttachment.getThreadId().getTypeId().getTypeName());
			data.setAttachmentId(threadAttachment.getAttachmentId().getId());
			data.setAttachmentCode(threadAttachment.getAttachmentId().getAttachmentCode());
			data.setAttachmentExtension(threadAttachment.getAttachmentId().getAttachmentExtension());
			data.setVersion(threadAttachment.getVersion());
			data.setIsActive(threadAttachment.getIsActive());

			listThreadAttachment.add(data);
		}

		getByThread.setData(listThreadAttachment);
		getByThread.setMsg(null);

		return getByThread;
	}
}
