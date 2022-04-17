package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.dto.threadcategory.DeleteByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetAllThreadCategoryDtoDataRes;
import com.lawencon.community.dto.threadcategory.GetAllThreadCategoryDtoRes;
import com.lawencon.community.dto.threadcategory.GetByThreadCategoryIdDtoDataRes;
import com.lawencon.community.dto.threadcategory.GetByThreadCategoryIdDtoRes;
import com.lawencon.community.dto.threadcategory.GetThreadCategoryByThreadDtoDataRes;
import com.lawencon.community.dto.threadcategory.GetThreadCategoryByThreadDtoRes;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoDataRes;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoReq;
import com.lawencon.community.dto.threadcategory.InsertThreadCategoryDtoRes;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Thread;
import com.lawencon.community.model.ThreadCategory;
import com.lawencon.community.service.ThreadCategoryService;

@Service
public class ThreadCategoryServiceImpl extends BaseService implements ThreadCategoryService {
	private ThreadCategoryDao threadCategoryDao;
	private CategoryDao categoryDao;
	private ThreadDao threadDao;

	@Autowired
	public ThreadCategoryServiceImpl(ThreadCategoryDao threadCategoryDao, CategoryDao categoryDao, ThreadDao threadDao) {
		this.threadCategoryDao = threadCategoryDao;
		this.categoryDao = categoryDao;
		this.threadDao = threadDao;
	}
	
	@Override
	public GetAllThreadCategoryDtoRes findAll() throws Exception {
		GetAllThreadCategoryDtoRes getAll = new GetAllThreadCategoryDtoRes();

		List<ThreadCategory> threadCategories = threadCategoryDao.findAll();
		List<GetAllThreadCategoryDtoDataRes> listThreadCategory = new ArrayList<>();

		for (int i = 0; i < threadCategories.size(); i++) {
			ThreadCategory threadCategory = threadCategories.get(i);
			GetAllThreadCategoryDtoDataRes data = new GetAllThreadCategoryDtoDataRes();

			data.setId(threadCategory.getId());
			data.setCategoryId(threadCategory.getCategoryId().getId());
			data.setCategoryName(threadCategory.getCategoryId().getCategoryName());
			data.setThreadId(threadCategory.getThreadId().getId());
			data.setThreadTitle(threadCategory.getThreadId().getThreadTitle());
			data.setThreadContent(threadCategory.getThreadId().getThreadContent());
			data.setVersion(threadCategory.getVersion());
			data.setIsActive(threadCategory.getIsActive());

			listThreadCategory.add(data);
		}

		getAll.setData(listThreadCategory);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByThreadCategoryIdDtoRes findById(String id) throws Exception {
		GetByThreadCategoryIdDtoRes getById = new GetByThreadCategoryIdDtoRes();

		ThreadCategory threadCategory = threadCategoryDao.findById(id);
		GetByThreadCategoryIdDtoDataRes data = new GetByThreadCategoryIdDtoDataRes();

		data.setId(threadCategory.getId());
		data.setCategoryId(threadCategory.getCategoryId().getId());
		data.setCategoryName(threadCategory.getCategoryId().getCategoryName());
		data.setThreadId(threadCategory.getThreadId().getId());
		data.setThreadTitle(threadCategory.getThreadId().getThreadTitle());
		data.setThreadContent(threadCategory.getThreadId().getThreadContent());
		data.setVersion(threadCategory.getVersion());
		data.setIsActive(threadCategory.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertThreadCategoryDtoRes insert(InsertThreadCategoryDtoReq data) throws Exception {
		InsertThreadCategoryDtoRes insert = new InsertThreadCategoryDtoRes();

		try {
			validateInsert(data);
			
			ThreadCategory threadCategory = new ThreadCategory();
			
			Thread thread = threadDao.findById(data.getThreadId());
			threadCategory.setThreadId(thread);
			
			Category category = categoryDao.findById(data.getCategoryId());
			threadCategory.setCategoryId(category);
			threadCategory.setCreatedBy(getId());

			ThreadCategory threadCategoryInsert = threadCategoryDao.save(threadCategory);

			InsertThreadCategoryDtoDataRes dataDto = new InsertThreadCategoryDtoDataRes();
			dataDto.setId(threadCategoryInsert.getId());

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
	public DeleteByThreadCategoryIdDtoRes deleteById(String id) throws Exception {
		DeleteByThreadCategoryIdDtoRes deleteById = new DeleteByThreadCategoryIdDtoRes();

		try {
			begin();
			boolean isDeleted = threadCategoryDao.deleteById(id);
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
	public GetThreadCategoryByThreadDtoRes findByThread(String id) throws Exception {
		GetThreadCategoryByThreadDtoRes getByThread = new GetThreadCategoryByThreadDtoRes();

		List<ThreadCategory> threadCategories = threadCategoryDao.findByThread(id);
		List<GetThreadCategoryByThreadDtoDataRes> listThreadCategory = new ArrayList<>();

		for (int i = 0; i < threadCategories.size(); i++) {
			ThreadCategory threadCategory = threadCategories.get(i);
			GetThreadCategoryByThreadDtoDataRes data = new GetThreadCategoryByThreadDtoDataRes();

			data.setId(threadCategory.getId());
			data.setCategoryId(threadCategory.getCategoryId().getId());
			data.setCategoryName(threadCategory.getCategoryId().getCategoryName());
			data.setThreadId(threadCategory.getThreadId().getId());
			data.setThreadTitle(threadCategory.getThreadId().getThreadTitle());
			data.setThreadContent(threadCategory.getThreadId().getThreadContent());
			data.setVersion(threadCategory.getVersion());
			data.setIsActive(threadCategory.getIsActive());

			listThreadCategory.add(data);
		}

		getByThread.setData(listThreadCategory);
		getByThread.setMsg(null);

		return getByThread;
	}
	
	private void validateInsert(InsertThreadCategoryDtoReq data) throws Exception {
		if (data.getThreadId() == null || data.getThreadId().trim().equals("")) {
			throw new Exception("Thread Id Cant Null");
		} else {
			if (data.getCategoryId() == null || data.getCategoryId().trim().equals("")) {
				throw new Exception("Category Cant Null");
			}
		}
	}
}
