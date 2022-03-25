package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.service.ThreadCategoryService;

@Service
public class ThreadCategoryServiceImpl extends BaseService implements ThreadCategoryService {
	private ThreadCategoryDao threadCategoryDao;

	@Autowired
	public ThreadCategoryServiceImpl(threadCommentDao threadCategoryDao) {
		this.threadCategoryDao = threadCategoryDao;
	}
}
