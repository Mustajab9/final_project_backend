package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.SubscriptionDetail;

public interface SubscriptionDetailDao {
	public List<SubscriptionDetail> getBySubscription(String id) throws Exception;
}
