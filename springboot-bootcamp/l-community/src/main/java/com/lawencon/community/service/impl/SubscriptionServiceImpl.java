package com.lawencon.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.base.BaseServiceImpl;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.dao.UserDao;
import com.lawencon.community.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl extends BaseService implements SubscriptionService {
	private SubscriptionDao subscriptionDao;

	@Autowired
	public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
		this.subscriptionDao = subscriptionDao;
	}
}
