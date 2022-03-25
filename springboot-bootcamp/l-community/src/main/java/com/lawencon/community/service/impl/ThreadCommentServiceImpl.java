package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadCommentDao;
import com.lawencon.community.service.ThreadCommentService;

@Service
public class ThreadCommentServiceImpl extends BaseService implements ThreadCommentService {
	private ThreadCommentDao threadCommentDao;

	@Autowired
	public ThreadCommentServiceImpl(ThreadCommentDao threadCommentDao) {
		this.threadCommentDao = threadCommentDao;
	}
}
