package com.lawencon.community.dao;

import com.lawencon.community.model.Subscription;

public interface SubscriptionDao {
	public Subscription getByUser(String id) throws Exception;
}
