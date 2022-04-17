package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.SubscriptionDetail;

public interface SubscriptionDetailDao {
	List<SubscriptionDetail> findAll() throws Exception;
	SubscriptionDetail findById(String id) throws Exception;
	SubscriptionDetail save(SubscriptionDetail data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<SubscriptionDetail> findBySubscription(String id) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
