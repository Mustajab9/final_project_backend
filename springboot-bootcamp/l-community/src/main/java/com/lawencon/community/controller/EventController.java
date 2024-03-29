package com.lawencon.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.GetCountNotPaidDtoDataRes;
import com.lawencon.community.dto.event.GetEventByCategoryDtoRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;
import com.lawencon.community.service.EventService;
import com.lawencon.util.JasperUtil;

@RestController
@RequestMapping("events")
public class EventController {
	private EventService eventService;

	@Autowired
	public void setBookmarkService(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping("report/{eventId}")
	public ResponseEntity<?> getReportProfileAttendance(@PathVariable("eventId") String eventId) throws Exception {
		List<GetReportProfileAttendanceEventDto> data = eventService.getReportEnroll(eventId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", eventId);
		
		byte[] out = JasperUtil.responseToByteArray(data, "member_list_event", map);
		
		String fileName = "member list " + data.get(0).getEventTitle() + ".pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}
	
	@GetMapping("report/income_report/{eventId}")
	public ResponseEntity<?> getReportIncome(@PathVariable("eventId") String eventId) throws Exception {
		List<GetReportIncomeEventDto> data = eventService.getReportIncome(eventId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("id", eventId);
		
		byte[] out = JasperUtil.responseToByteArray(data, "income_list_event", map);
		
		String fileName = "income event " + data.get(0).getEventTitle() + ".pdf";
		
		return ResponseEntity.ok()
				.contentType(MediaType.APPLICATION_PDF)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName+ "\"")
				.body(out);
	}
	
	@GetMapping
	public ResponseEntity<GetAllEventDtoRes> getAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllEventDtoRes result = eventService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllEventDtoRes>(result, HttpStatus.OK);
	}

	
	@GetMapping("{id}")
	public ResponseEntity<GetByEventIdDtoRes> getById(@PathVariable("id") String id) throws Exception {
		GetByEventIdDtoRes data = eventService.findById(id);
		return new ResponseEntity<GetByEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("enroll/{id}")
	public ResponseEntity<GetAllEventDtoRes> getByEnroll(@PathVariable("id") String id) throws Exception {
		GetAllEventDtoRes data = eventService.findEnrollEvent(id);
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("not-enroll")
	public ResponseEntity<GetAllEventDtoRes> getByNotEnroll() throws Exception {
		GetAllEventDtoRes data = eventService.findNotEnrollEvent();
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("enroll-status/{id}/{isApprove}")
	public ResponseEntity<GetAllEventDtoRes> getEnrollStatus(@PathVariable("id") String id, @PathVariable("isApprove") boolean isApprove) throws Exception {
		GetAllEventDtoRes data = eventService.findEnrollStatus(id, isApprove);
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("event-not-approve/{id}")
	public ResponseEntity<GetByEventIdDtoRes> getByNotEnroll(@PathVariable("id") String id) throws Exception {
		GetByEventIdDtoRes data = eventService.findEventStatus(id);
		return new ResponseEntity<GetByEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("event-not-paid/{id}")
	public ResponseEntity<GetAllEventDtoRes> getEventNotPaid(@PathVariable("id") String id) throws Exception {
		GetAllEventDtoRes data = eventService.findEventNotPaid(id);
		return new ResponseEntity<GetAllEventDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("count-not-paid")
	public ResponseEntity<GetCountNotPaidDtoDataRes> getCountNotPaid() throws Exception {
		GetCountNotPaidDtoDataRes data = eventService.countNotPaid();
		return new ResponseEntity<GetCountNotPaidDtoDataRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("category/{id}")
	public ResponseEntity<GetEventByCategoryDtoRes> getByCategory(@PathVariable("id") String id) throws Exception {
		GetEventByCategoryDtoRes data = eventService.findByCategory(id);
		return new ResponseEntity<GetEventByCategoryDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("nl")
	public ResponseEntity<GetAllEventDtoRes> getAllNl(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllEventDtoRes result = eventService.findAll(query, startPage, maxPage);
		return new ResponseEntity<GetAllEventDtoRes>(result, HttpStatus.OK);
	}

	
	@GetMapping("nl/{id}")
	public ResponseEntity<GetByEventIdDtoRes> getByIdNl(@PathVariable("id") String id) throws Exception {
		GetByEventIdDtoRes data = eventService.findById(id);
		return new ResponseEntity<GetByEventIdDtoRes>(data, HttpStatus.OK);
	}
	
	@GetMapping("nl/category/{id}")
	public ResponseEntity<GetEventByCategoryDtoRes> getByCategoryNl(@PathVariable("id") String id) throws Exception {
		GetEventByCategoryDtoRes data = eventService.findByCategory(id);
		return new ResponseEntity<GetEventByCategoryDtoRes>(data, HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<DeleteByEventIdDtoRes> deleteById(@PathVariable("id") String id) throws Exception {
		DeleteByEventIdDtoRes data = eventService.deleteById(id);
		return new ResponseEntity<DeleteByEventIdDtoRes>(data, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsertEventDtoRes> insertData(@RequestPart(name = "content") String content, @RequestPart(required = false) MultipartFile file) throws Exception {
		InsertEventDtoRes insert = eventService.insert(content, file);
		return new ResponseEntity<InsertEventDtoRes>(insert, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<UpdateEventDtoRes> updateData(@RequestBody @Valid UpdateEventDtoReq data) throws Exception {
		UpdateEventDtoRes update = eventService.update(data);
		return new ResponseEntity<UpdateEventDtoRes>(update, HttpStatus.OK);
	}
}
