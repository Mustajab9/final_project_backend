package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Event;

public interface EventDao {
	public List<Event> getEnrollEvent(String id) throws Exception;
	public List<Event> getNotEnrollEvent(String id) throws Exception;
}
