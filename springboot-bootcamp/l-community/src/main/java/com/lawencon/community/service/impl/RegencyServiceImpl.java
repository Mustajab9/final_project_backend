package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.RegencyDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.RegencyService;

@Service
public class RegencyServiceImpl extends BaseService implements RegencyService {
	private RegencyDao regencyDao;

	@Autowired
	public RegencyServiceImpl(RegencyDao regencyDao) {
		this.regencyDao = regencyDao;
	}
}
