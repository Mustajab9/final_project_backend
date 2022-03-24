package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadCategory;

public interface ThreadCategoryDao {
	public List<ThreadCategory> findAll() throws Exception;
	public ThreadCategory findById(String id) throws Exception;
	public ThreadCategory save(ThreadCategory data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<ThreadCategory> findByThread(String id) throws Exception;
}
