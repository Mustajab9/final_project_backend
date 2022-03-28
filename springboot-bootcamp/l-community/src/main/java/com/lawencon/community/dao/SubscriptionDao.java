package com.lawencon.community.dao;

import java.util.Date;
import java.util.List;

import com.lawencon.community.model.Subscription;

public interface SubscriptionDao {
	public List<Subscription> findAll(int startPage, int maxPage) throws Exception;
	public Subscription findById(String id) throws Exception;
	public Subscription save(Subscription data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public boolean update(Date date, Integer lenghtDay, String id) throws Exception;
	public Subscription findByUser(String id) throws Exception;
}
