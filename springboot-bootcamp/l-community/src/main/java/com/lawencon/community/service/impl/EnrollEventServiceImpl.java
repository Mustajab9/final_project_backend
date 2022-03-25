package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.EnrollEventService;

@Service
public class EnrollEventServiceImpl extends BaseService implements EnrollEventService {
	private EnrollEventDao enrollEventDao;

	@Autowired
	public EnrollEventServiceImpl(EnrollEventDao enrollEventDao) {
		this.enrollEventDao = enrollEventDao;
	}
}
