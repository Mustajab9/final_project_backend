package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.dto.event.GetAllEventDtoDataRes;
import com.lawencon.community.dto.event.GetCountNotPaidDtoDataRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.model.Event;
import com.lawencon.model.SearchQuery;

public interface EventDao {
	SearchQuery<Event> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Event findById(String id) throws Exception;
	Event save(Event data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<GetAllEventDtoDataRes> findEnrollEvent(String id) throws Exception;
	List<Event> findNotEnrollEvent(String id) throws Exception;
	List<GetReportProfileAttendanceEventDto> getReportEnrolls(String eventId) throws Exception;
	List<GetReportIncomeEventDto> getReportIncome(String eventId) throws Exception;
	Long countAll() throws Exception;
	List<GetAllEventDtoDataRes> findEnrollStatus(String id, boolean isApprove) throws Exception;
	List<GetAllEventDtoDataRes> findEventNotApprove(String id, boolean isApprove) throws Exception;
	List<GetAllEventDtoDataRes> findEventNotPaid(String id) throws Exception;
	GetCountNotPaidDtoDataRes countNotPaid(String id) throws Exception;
}
