package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingDao;
import com.lawencon.community.service.PollingService;

@Service
public class PollingServiceImpl extends BaseService implements PollingService {
	private PollingDao pollingDao;

	@Autowired
	public PollingServiceImpl(PollingDao pollingDao) {
		this.pollingDao = pollingDao;
	}
}
