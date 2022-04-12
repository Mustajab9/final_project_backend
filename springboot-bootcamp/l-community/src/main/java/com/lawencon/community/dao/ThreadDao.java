package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Thread;

public interface ThreadDao {
	List<Thread> findAll(Integer startPage, Integer maxPage) throws Exception;
	Thread findById(String id) throws Exception;
	Thread save(Thread data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<Thread> findByUser(String id) throws Exception;
	List<Thread> findByCategory(String id[]) throws Exception;
}
