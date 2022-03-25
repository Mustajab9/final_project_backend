package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadDao;
import com.lawencon.community.service.ThreadService;

@Service
public class ThreadServiceImpl extends BaseService implements ThreadService {
	private ThreadDao threadDao;

	@Autowired
	public ThreadServiceImpl(ThreadDao threadDao) {
		this.threadDao = threadDao;
	}
}
