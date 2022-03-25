package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.ProvinceService;

@Service
public class ProvinceServiceImpl extends BaseService implements ProvinceService {
	private ProvinceDao provinceDao;

	@Autowired
	public ProvinceServiceImpl(ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}
}
