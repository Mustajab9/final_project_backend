package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.model.Event;
import com.lawencon.model.SearchQuery;

public interface EventDao {
//	public List<Event> findAll() throws Exception;
	public SearchQuery<Event> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Event findById(String id) throws Exception;
	public Event save(Event data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Event> findEnrollEvent(String id) throws Exception;
	public List<Event> findNotEnrollEvent(String id) throws Exception;
	public List<GetReportProfileAttendanceEventDto> getReportEnrolls(String eventId) throws Exception;
	public List<GetReportIncomeEventDto> getReportIncome(String eventId) throws Exception;
	public Long countAll() throws Exception;
}
