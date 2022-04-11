package com.lawencon.community.dao;

import java.util.Date;

import com.lawencon.community.model.Subscription;
import com.lawencon.model.SearchQuery;

public interface SubscriptionDao {
	SearchQuery<Subscription> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Subscription findById(String id) throws Exception;
	Subscription save(Subscription data) throws Exception;
	boolean deleteById(String id) throws Exception;
	boolean update(Date date, Integer lenghtDay, String id, String userId) throws Exception;
	Subscription findByUser(String id) throws Exception;
	Long countAll() throws Exception;
}
