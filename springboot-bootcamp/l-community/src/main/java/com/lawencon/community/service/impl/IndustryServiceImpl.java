package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.IndustryService;

@Service
public class IndustryServiceImpl extends BaseService implements IndustryService {
	private IndustryDao industryDao;

	@Autowired
	public IndustryServiceImpl(IndustryDao industryDao) {
		this.industryDao = industryDao;
	}
}
