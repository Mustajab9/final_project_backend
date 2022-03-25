package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ThreadTypeService;

@Service
public class ThreadTypeServiceImpl extends BaseService implements ThreadTypeService {
	private ThreadTypeDao typeDao;

	@Autowired
	public ThreadTypeServiceImpl(ThreadTypeDao typeDao) {
		this.typeDao = typeDao;
	}
}
