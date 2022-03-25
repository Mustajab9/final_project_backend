package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.EventService;

@Service
public class EventServiceImpl extends BaseService implements EventService {
	private EventDao eventDao;

	@Autowired
	public EventServiceImpl(EventDao eventDao) {
		this.eventDao = eventDao;
	}
}
