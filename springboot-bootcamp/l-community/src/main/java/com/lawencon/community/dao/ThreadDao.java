package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Thread;

public interface ThreadDao {
	public List<Thread> getByUser(String id) throws Exception;
	public List<Thread> getByCategory(String id[]) throws Exception;
}
