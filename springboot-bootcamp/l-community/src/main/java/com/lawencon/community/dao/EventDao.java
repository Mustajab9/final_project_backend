package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Event;

public interface EventDao {
	public List<Event> findAll() throws Exception;
	public Event findById(String id) throws Exception;
	public Event save(Event data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Event> findEnrollEvent(String id) throws Exception;
	public List<Event> findNotEnrollEvent(String id) throws Exception;
	public List<Event> getReportEnrolls(String eventId) throws Exception;
}
