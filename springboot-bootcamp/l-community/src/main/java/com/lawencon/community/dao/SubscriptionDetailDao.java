package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.SubscriptionDetail;

public interface SubscriptionDetailDao {
	public List<SubscriptionDetail> findAll() throws Exception;
	public SubscriptionDetail findById(String id) throws Exception;
	public SubscriptionDetail save(SubscriptionDetail data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<SubscriptionDetail> findBySubscription(String id) throws Exception;
}
