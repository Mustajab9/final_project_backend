package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.ThreadCategory;

public interface ThreadCategoryDao {
	List<ThreadCategory> findAll() throws Exception;
	ThreadCategory findById(String id) throws Exception;
	ThreadCategory save(ThreadCategory data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<ThreadCategory> findByThread(String id) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
