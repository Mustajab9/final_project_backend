package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.EventType;

public interface EventTypeDao {
	public List<EventType> findAll(int startPage, int maxPage) throws Exception;
	public EventType findById(String id) throws Exception;
	public EventType save(EventType data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
