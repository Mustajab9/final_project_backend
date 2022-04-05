package com.lawencon.community.dao;

import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

public interface ThreadTypeDao {
	public SearchQuery<ThreadType> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public ThreadType findById(String id) throws Exception;
	public ThreadType save(ThreadType data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
