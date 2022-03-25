package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.ThreadLikeDao;
import com.lawencon.community.service.ThreadLikeService;

@Service
public class ThreadLikeServiceImpl extends BaseService implements ThreadLikeService {
	private ThreadLikeDao threadLikeDao;

	@Autowired
	public ThreadLikeServiceImpl(ThreadLikeDao threadLikeDao) {
		this.threadLikeDao = threadLikeDao;
	}
}
