package com.lawencon.community.dao.impl;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.SubscriptionDao;
import com.lawencon.community.model.Subscription;

@Repository
public class SubscriptionDaoImpl extends BaseDaoImpl<Subscription> implements SubscriptionDao {
	@Override
	public Subscription getByUser(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
