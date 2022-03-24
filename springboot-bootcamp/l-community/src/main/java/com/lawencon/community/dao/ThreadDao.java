package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Thread;

public interface ThreadDao {
	public List<Thread> findAll() throws Exception;
	public Thread findById(String id) throws Exception;
	public Thread save(Thread data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Thread> findByUser(String id) throws Exception;
	public List<Thread> findByCategory(String id[]) throws Exception;
}
