package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.PollingChoiceDao;
import com.lawencon.community.service.PollingChoiceService;

@Service
public class PollingChoiceServiceImpl extends BaseService implements PollingChoiceService {
	private PollingChoiceDao pollingChoiceDao;

	@Autowired
	public PollingChoiceServiceImpl(PollingChoiceDao pollingChoiceDao) {
		this.pollingChoiceDao = pollingChoiceDao;
	}
}
