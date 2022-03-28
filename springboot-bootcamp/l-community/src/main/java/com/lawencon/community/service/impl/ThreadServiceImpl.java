package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dto.attachment.InsertAttachmentDtoRes;
import com.lawencon.community.dto.thread.DeleteByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoDataRes;
import com.lawencon.community.dto.thread.GetAllThreadDtoRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoDataRes;
import com.lawencon.community.dto.thread.GetByThreadIdDtoRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadByCategoryDtoRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoDataRes;
import com.lawencon.community.dto.thread.GetThreadByUserDtoRes;
import com.lawencon.community.dto.thread.InsertThreadDtoDataRes;
import com.lawencon.community.dto.thread.InsertThreadDtoReq;
import com.lawencon.community.dto.thread.InsertThreadDtoRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoDataRes;
import com.lawencon.community.dto.thread.UpdateThreadDtoReq;
import com.lawencon.community.dto.thread.UpdateThreadDtoRes;
import com.lawencon.community.dto.threadattachment.InsertThreadAttachmentDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadAttachment;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.model.ThreadType;
import com.lawencon.community.service.AttachmentService;
import com.lawencon.community.service.ThreadAttachmentService;
import com.lawencon.community.service.ThreadCategoryService;
import com.lawencon.community.service.ThreadService;

@Service
public class ThreadServiceImpl extends BaseService implements ThreadService {
	private ThreadDao threadDao;
	private ThreadTypeDao threadTypeDao;
	private ThreadAttachmentDao threadAttachmentDao;
	private ThreadCategoryDao threadCategoryDao;
	private AttachmentService attachmentService;
	private ThreadAttachmentService threadAttachmentService;
	private ThreadCategoryService threadCategoryService;

	@Autowired
	public ThreadServiceImpl(ThreadDao threadDao, ThreadTypeDao threadTypeDao, ThreadAttachmentDao threadAttachmentDao, ThreadCategoryDao threadCategoryDao) {
		this.threadDao = threadDao;
		this.threadTypeDao = threadTypeDao;
		this.threadAttachmentDao = threadAttachmentDao;
		this.threadCategoryDao = threadCategoryDao;
	}
	
	@Autowired
	public void setAttachmentService(AttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
	@Autowired
	public void setThreadAttachmentService(ThreadAttachmentService threadAttachmentService) {
		this.threadAttachmentService = threadAttachmentService;
	}
	
	@Autowired
	public void setThreadCategoryService(ThreadCategoryService threadCategoryService) {
		this.threadCategoryService = threadCategoryService;
	}
	
	@Override
	public GetAllThreadDtoRes findAll() throws Exception {
		GetAllThreadDtoRes getAll = new GetAllThreadDtoRes();

		List<Thread> threads = threadDao.findAll();
		List<GetAllThreadDtoDataRes> listThread = new ArrayList<>();

		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			GetAllThreadDtoDataRes data = new GetAllThreadDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int x = 0; x < attachments.size(); x++) {
					ThreadAttachment attcahment = attachments.get(x);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
						 
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getAll.setData(listThread);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadIdDtoRes findById(String id) throws Exception {
		GetByThreadIdDtoRes getById = new GetByThreadIdDtoRes();

		Thread thread = threadDao.findById(id);
		GetByThreadIdDtoDataRes data = new GetByThreadIdDtoDataRes();

		data.setId(thread.getId());
		data.setThreadCode(thread.getThreadCode());
		data.setThreadTitle(thread.getThreadTitle());
		data.setThreadContent(thread.getThreadContent());
		
		List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
		List<String> listCategoryId = new ArrayList<>();
		List<String> listCategoryName = new ArrayList<>();
		for(int x = 0; x < categories.size(); x++) {
			ThreadCategory category = categories.get(x);
			
			String categoryId = category.getCategoryId().getId();
			String categoryName = category.getCategoryId().getCategoryName();
			
			listCategoryId.add(categoryId);
			listCategoryName.add(categoryName);
		}
		
		data.setCategoryId(listCategoryId);
		data.setCategoryName(listCategoryName);
		
		List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
		
		if(attachments != null) {
			List<String> listAttachmentId = new ArrayList<>();
			List<String> listAttachemntExtension = new ArrayList<>();
			for(int x = 0; x < attachments.size(); x++) {
				ThreadAttachment attcahment = attachments.get(x);
				
				String attachmentId = attcahment.getAttachmentId().getId();
				String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
				
				listAttachmentId.add(attachmentId);
				listAttachemntExtension.add(attachemntExtension);
			}
			
			data.setAttachmentId(listAttachmentId);
			data.setAttachemntExtension(listAttachemntExtension);
		}
					 
		data.setVersion(thread.getVersion());
		data.setIsActive(thread.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadDtoRes insert(String content, MultipartFile[] file) throws Exception {
		InsertThreadDtoRes insert = new InsertThreadDtoRes();

		try {
			InsertThreadDtoReq data = new ObjectMapper().readValue(content, InsertThreadDtoReq.class);
			Thread thread = new Thread();
			String code = getAlphaNumericString(5);
			
			thread.setThreadCode(code);
			thread.setThreadTitle(data.getThreadTitle());
			thread.setThreadContent(data.getThreadContent());
			
			if(data.getIsPremium() != null) {
				thread.setIsPremium(data.getIsPremium());
			}
			
			ThreadType type = threadTypeDao.findById(data.getTypeId());
			thread.setTypeId(type);
			thread.setCreatedBy(getId());

			begin();
			Thread threadInsert = threadDao.save(thread);
			commit();

			InsertThreadDtoDataRes dataDto = new InsertThreadDtoDataRes();
			dataDto.setId(threadInsert.getId());
			
			if(threadInsert != null) {
				for(int i = 0; i < data.getCategoryId().size(); i++) {
					InsertThreadCategoryDtoReq categoryReq = new InsertThreadCategoryDtoReq();
					categoryReq.setThreadId(threadInsert.getId());				
					categoryReq.setCategoryId(data.getCategoryId().get(i));
					
					threadCategoryService.insert(categoryReq);
				}
				
				if(file != null) {
					for(int j = 0; j < file.length; j++) {
						InsertAttachmentDtoRes attachmentRes = attachmentService.insert(file[j]);
						
						if(attachmentRes != null) {
							InsertThreadAttachmentDtoReq attachmentReq = new InsertThreadAttachmentDtoReq();
							attachmentReq.setAttachmentId(attachmentRes.getData().getId());
							attachmentReq.setThreadId(threadInsert.getId());
							
							threadAttachmentService.insert(attachmentReq);
						}
					}
				}
			}
			
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
	public UpdateThreadDtoRes update(UpdateThreadDtoReq data) throws Exception {
		UpdateThreadDtoRes update = new UpdateThreadDtoRes();

		try {
			if (data.getVersion() != null) {
				Thread thread = threadDao.findById(data.getId());

				thread.setThreadTitle(data.getThreadTitle());
				thread.setThreadContent(data.getThreadContent());
				thread.setIsPremium(data.getIsPremium());
				
				ThreadType type = threadTypeDao.findById(data.getTypeId());
				thread.setTypeId(type);				
				thread.setUpdatedBy(getId());

				if (data.getIsActive() != null) {
					thread.setIsActive(data.getIsActive());
				}

				begin();
				Thread threadUpdate = threadDao.save(thread);
				commit();

				UpdateThreadDtoDataRes dataDto = new UpdateThreadDtoDataRes();
				dataDto.setVersion(threadUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg("Update Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByThreadIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadIdDtoRes deleteById = new DeleteByThreadIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetThreadByUserDtoRes findByUser(String id) throws Exception {
		GetThreadByUserDtoRes getByUser = new GetThreadByUserDtoRes();

		List<Thread> threads = threadDao.findByUser(id);
		List<GetThreadByUserDtoDataRes> listThread = new ArrayList<>();

		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			GetThreadByUserDtoDataRes data = new GetThreadByUserDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int x = 0; x < attachments.size(); x++) {
					ThreadAttachment attcahment = attachments.get(x);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
						 
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getByUser.setData(listThread);
		getByUser.setMsg(null);

		return getByUser;
	}
	
	@Override
	public GetThreadByCategoryDtoRes findByCategory(String[] id) throws Exception {
		GetThreadByCategoryDtoRes getByCategory = new GetThreadByCategoryDtoRes();

		List<Thread> threads = threadDao.findByCategory(id);
		List<GetThreadByCategoryDtoDataRes> listThread = new ArrayList<>();

		for (int i = 0; i < threads.size(); i++) {
			Thread thread = threads.get(i);
			GetThreadByCategoryDtoDataRes data = new GetThreadByCategoryDtoDataRes();

			data.setId(thread.getId());
			data.setThreadCode(thread.getThreadCode());
			data.setThreadTitle(thread.getThreadTitle());
			data.setThreadContent(thread.getThreadContent());
			
			List<ThreadCategory> categories = threadCategoryDao.findByThread(thread.getId());		
			List<String> listCategoryId = new ArrayList<>();
			List<String> listCategoryName = new ArrayList<>();
			for(int x = 0; x < categories.size(); x++) {
				ThreadCategory category = categories.get(x);
				
				String categoryId = category.getCategoryId().getId();
				String categoryName = category.getCategoryId().getCategoryName();
				
				listCategoryId.add(categoryId);
				listCategoryName.add(categoryName);
			}
			
			data.setCategoryId(listCategoryId);
			data.setCategoryName(listCategoryName);
			
			List<ThreadAttachment> attachments = threadAttachmentDao.findByThread(thread.getId());
			
			if(attachments != null) {
				List<String> listAttachmentId = new ArrayList<>();
				List<String> listAttachemntExtension = new ArrayList<>();
				for(int x = 0; x < attachments.size(); x++) {
					ThreadAttachment attcahment = attachments.get(x);
					
					String attachmentId = attcahment.getAttachmentId().getId();
					String attachemntExtension = attcahment.getAttachmentId().getAttachmentExtension();
					
					listAttachmentId.add(attachmentId);
					listAttachemntExtension.add(attachemntExtension);
				}
				
				data.setAttachmentId(listAttachmentId);
				data.setAttachemntExtension(listAttachemntExtension);
			}
						 
			data.setVersion(thread.getVersion());
			data.setIsActive(thread.getIsActive());

			listThread.add(data);
		}

		getByCategory.setData(listThread);
		getByCategory.setMsg(null);

		return getByCategory;
	}
}
