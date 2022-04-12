package com.lawencon.community.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.GetCountNotPaidDtoDataRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;

public interface EventService {
	GetAllEventDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	GetByEventIdDtoRes findById(String id) throws Exception;
	InsertEventDtoRes insert(String data, MultipartFile file) throws Exception;
	UpdateEventDtoRes update(UpdateEventDtoReq data) throws Exception;
	DeleteByEventIdDtoRes deleteById(String id) throws Exception;
	GetAllEventDtoRes findEnrollEvent(String id) throws Exception;
	GetAllEventDtoRes findNotEnrollEvent() throws Exception;
	List<GetReportProfileAttendanceEventDto> getReportEnroll(String eventId) throws Exception;
	List<GetReportIncomeEventDto> getReportIncome(String eventId) throws Exception;
	GetAllEventDtoRes findEnrollStatus(String id, boolean isApprove) throws Exception;
	GetAllEventDtoRes findEventNotApprove(String id, boolean isApprove) throws Exception;
	GetAllEventDtoRes findEventNotPaid(String id) throws Exception;
	GetCountNotPaidDtoDataRes countNotPaid() throws Exception;
}
