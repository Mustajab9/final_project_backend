package com.lawencon.community.dao;

import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

public interface ThreadTypeDao {
	SearchQuery<ThreadType> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	ThreadType findById(String id) throws Exception;
	ThreadType save(ThreadType data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
