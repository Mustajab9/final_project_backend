package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.service.EventTypeService;

@Service
public class EventTypeServiceImpl extends BaseService implements EventTypeService {
	private EventTypeDao typeDao;

	@Autowired
	public EventTypeServiceImpl(EventTypeDao typeDao) {
		this.typeDao = typeDao;
	}
}
