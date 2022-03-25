package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.PriceListEventService;

@Service
public class PriceListEventServiceImpl extends BaseService implements PriceListEventService {
	private PriceListEventDao priceListEventDao;

	@Autowired
	public PriceListEventServiceImpl(PriceListEventDao priceListEventDao) {
		this.priceListEventDao = priceListEventDao;
	}
}
