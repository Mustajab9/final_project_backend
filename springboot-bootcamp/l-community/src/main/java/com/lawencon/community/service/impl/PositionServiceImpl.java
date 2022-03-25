package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.PositionService;

@Service
public class PositionServiceImpl extends BaseService implements PositionService {
	private PositionDao positionDao;

	@Autowired
	public PositionServiceImpl(PositionDao positionDao) {
		this.positionDao = positionDao;
	}
}
