package com.lawencon.community.dao;

import java.util.Date;

import com.lawencon.community.model.Subscription;
import com.lawencon.model.SearchQuery;

public interface SubscriptionDao {
	public SearchQuery<Subscription> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Subscription findById(String id) throws Exception;
	public Subscription save(Subscription data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public boolean update(Date date, Integer lenghtDay, String id, String userId) throws Exception;
	public Subscription findByUser(String id) throws Exception;
	public Long countAll() throws Exception;
}
