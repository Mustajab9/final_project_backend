package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ThreadAttachmentDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ThreadAttachmentService;

@Service
public class ThreadAttachmentServiceImpl extends BaseService implements ThreadAttachmentService {
	private ThreadAttachmentDao threadAttachmentDao;

	@Autowired
	public ThreadAttachmentServiceImpl(ThreadAttachmentDao threadAttachmentDao) {
		this.threadAttachmentDao = threadAttachmentDao;
	}
}
