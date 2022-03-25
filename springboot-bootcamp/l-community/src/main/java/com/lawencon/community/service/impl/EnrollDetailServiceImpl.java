package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.EnrollDetailDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.EnrollDetailService;

@Service
public class EnrollDetailServiceImpl extends BaseService implements EnrollDetailService  {
	private EnrollDetailDao enrollDetailDao;

	@Autowired
	public EnrollDetailServiceImpl(EnrollDetailDao enrollDetailDao) {
		this.enrollDetailDao = enrollDetailDao;
	}
}
