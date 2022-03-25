package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.SubscriptionDetailDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.SubscriptionDetailService;

@Service
public class SubscriptionDetailDaoImpl extends BaseService implements SubscriptionDetailService {
	private SubscriptionDetailDao subscriptionDetailDao;

	@Autowired
	public SubscriptionDetailDaoImpl(SubscriptionDetailDao subscriptionDetailDao) {
		this.subscriptionDetailDao = subscriptionDetailDao;
	}
}
