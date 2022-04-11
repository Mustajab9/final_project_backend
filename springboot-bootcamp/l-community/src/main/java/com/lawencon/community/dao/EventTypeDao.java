package com.lawencon.community.dao;

import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

public interface EventTypeDao {
	SearchQuery<EventType> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	EventType findById(String id) throws Exception;
	EventType save(EventType data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
