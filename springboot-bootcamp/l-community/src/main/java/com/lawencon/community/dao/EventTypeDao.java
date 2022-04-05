package com.lawencon.community.dao;

import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

public interface EventTypeDao {
	public SearchQuery<EventType> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public EventType findById(String id) throws Exception;
	public EventType save(EventType data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
