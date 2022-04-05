package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoDataRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoDataRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.dto.event.InsertEventDtoDataRes;
import com.lawencon.community.dto.event.InsertEventDtoReq;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.UpdateEventDtoDataRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.PriceListEvent;
import com.lawencon.community.service.EventService;

@Service
public class EventServiceImpl extends BaseService implements EventService {
	private EventDao eventDao;
	private EventTypeDao eventTypeDao;
	private CategoryDao categoryDao;
	private AttachmentDao attachmentDao;
	private PriceListEventDao priceListEventDao;

	@Autowired
	public EventServiceImpl(EventDao eventDao, EventTypeDao eventTypeDao, 
			CategoryDao categoryDao, AttachmentDao attachmentDao, PriceListEventDao priceListEventDao) {
		this.eventDao = eventDao;
		this.eventTypeDao = eventTypeDao;
		this.categoryDao = categoryDao;
		this.attachmentDao = attachmentDao;
		this.priceListEventDao = priceListEventDao;
	}
	
	@Override
	public GetAllEventDtoRes findAll() throws Exception {
		GetAllEventDtoRes getAll = new GetAllEventDtoRes();

		List<Event> events = eventDao.findAll();
		List<GetAllEventDtoDataRes> listEvent = new ArrayList<>();

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(event.getId());
			data.setEventCode(event.getEventCode());
			data.setEventTitle(event.getEventTitle());
			data.setEventProvider(event.getEventProvider());
			data.setEventPrice(event.getEventPrice());
			data.setEventTimeStart(event.getEventTimeStart());
			data.setEventTimeEnd(event.getEventTimeEnd());
			data.setEventDateStart(event.getEventDateStart());
			data.setEventDateEnd(event.getEventDateEnd());
			data.setIsApprove(event.getIsApprove());
			data.setCategoryId(event.getCategoryId().getId());
			data.setCategoryName(event.getCategoryId().getCategoryName());
			data.setTypeId(event.getTypeId().getId());
			data.setTypeName(event.getTypeId().getTypeName());
			data.setPriceId(event.getPriceId().getId());
			data.setPriceName(event.getPriceId().getPriceName());
			
			if(event.getAttachmentId() != null) {
				data.setAttachmentId(event.getAttachmentId().getId());
				data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
			}
			
			data.setVersion(event.getVersion());
			data.setIsActive(event.getIsActive());

			listEvent.add(data);
		}

		getAll.setData(listEvent);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByEventIdDtoRes findById(String id) throws Exception {
		GetByEventIdDtoRes getById = new GetByEventIdDtoRes();

		Event event = eventDao.findById(id);
		GetByEventIdDtoDataRes data = new GetByEventIdDtoDataRes();

		data.setEventCode(event.getEventCode());
		data.setEventTitle(event.getEventTitle());
		data.setEventProvider(event.getEventProvider());
		data.setEventPrice(event.getEventPrice());
		data.setEventTimeStart(event.getEventTimeStart());
		data.setEventTimeEnd(event.getEventTimeEnd());
		data.setEventDateStart(event.getEventDateStart());
		data.setEventDateEnd(event.getEventDateEnd());
		data.setIsApprove(event.getIsApprove());
		data.setCategoryId(event.getCategoryId().getId());
		data.setCategoryName(event.getCategoryId().getCategoryName());
		data.setTypeId(event.getTypeId().getId());
		data.setTypeName(event.getTypeId().getTypeName());
		data.setPriceId(event.getPriceId().getId());
		data.setPriceName(event.getPriceId().getPriceName());
		
		if(event.getAttachmentId() != null) {
			data.setAttachmentId(event.getAttachmentId().getId());
			data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
		}
		
		data.setVersion(event.getVersion());
		data.setIsActive(event.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertEventDtoRes insert(String data, MultipartFile file) throws Exception {
		InsertEventDtoRes insert = new InsertEventDtoRes();

		try {
			InsertEventDtoReq dataInsert = new ObjectMapper().readValue(data, InsertEventDtoReq.class);
			Event event = new Event();
			event.setEventCode(getAlphaNumericString(5));
			event.setEventTitle(dataInsert.getEventTitle());
			event.setEventProvider(dataInsert.getEventProvider());
			event.setEventPrice(dataInsert.getEventPrice());
			event.setEventTimeStart(dataInsert.getEventTimeStart());
			event.setEventTimeEnd(dataInsert.getEventTimeEnd());
			event.setEventDateStart(dataInsert.getEventDateStart());
			event.setEventDateEnd(dataInsert.getEventDateEnd());
			
			EventType eventType = eventTypeDao.findById(dataInsert.getEventTypeId());
			event.setTypeId(eventType);
			
			Category category = categoryDao.findById(dataInsert.getCategoryId());
			event.setCategoryId(category);
			
			PriceListEvent priceListEvent = priceListEventDao.findById(dataInsert.getPriceId());
			event.setPriceId(priceListEvent);
			
			event.setCreatedBy(getId());
			
			if(file != null) {
				Attachment attachment = new Attachment();
				attachment.setAttachmentCode(getAlphaNumericString(5));
				attachment.setAttachmentContent(file.getBytes());
				
				String extension = fileExtensionName(file);
				attachment.setAttachmentExtension(extension);
				attachment.setCreatedBy(getId());
				
				begin();
				Attachment attachmentInsert = attachmentDao.save(attachment);
				commit();
				event.setAttachmentId(attachmentInsert);
			}
			
			begin();
			Event eventInsert = eventDao.save(event);
			commit();

			InsertEventDtoDataRes dataDto = new InsertEventDtoDataRes();
			dataDto.setId(eventInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Event " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateEventDtoRes update(UpdateEventDtoReq data) throws Exception {
		UpdateEventDtoRes update = new UpdateEventDtoRes();

		try {
			if (data.getVersion() != null) {
				Event event = eventDao.findById(data.getId());
				event.setIsApprove(data.getIsApprove());
				event.setUpdatedBy(getId());
				event.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					event.setIsActive(data.getIsActive());
				}

				begin();
				Event eventUpdate = eventDao.save(event);
				commit();

				UpdateEventDtoDataRes dataDto = new UpdateEventDtoDataRes();
				dataDto.setVersion(eventUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Event " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByEventIdDtoRes deleteById(String id) throws Exception {
		DeleteByEventIdDtoRes deleteById = new DeleteByEventIdDtoRes();

		try {
			begin();
			boolean isDeleted = eventDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Evenet " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetAllEventDtoRes findEnrollEvent(String id) throws Exception {
		GetAllEventDtoRes getEnrollEvent = new GetAllEventDtoRes();

		List<Event> events = eventDao.findEnrollEvent(id);
		List<GetAllEventDtoDataRes> listEvent = new ArrayList<>();

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(event.getId());
			data.setEventCode(event.getEventCode());
			data.setEventTitle(event.getEventTitle());
			data.setEventProvider(event.getEventProvider());
			data.setEventPrice(event.getEventPrice());
			data.setEventTimeStart(event.getEventTimeStart());
			data.setEventTimeEnd(event.getEventTimeEnd());
			data.setEventDateStart(event.getEventDateStart());
			data.setEventDateEnd(event.getEventDateEnd());
			data.setIsApprove(event.getIsApprove());
			data.setCategoryId(event.getCategoryId().getId());
			data.setCategoryName(event.getCategoryId().getCategoryName());
			data.setTypeId(event.getTypeId().getId());
			data.setTypeName(event.getTypeId().getTypeName());
			data.setPriceId(event.getPriceId().getId());
			data.setPriceName(event.getPriceId().getPriceName());
			
			if(event.getAttachmentId() != null) {
				data.setAttachmentId(event.getAttachmentId().getId());
				data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
			}
			
			data.setVersion(event.getVersion());
			data.setIsActive(event.getIsActive());

			listEvent.add(data);
		}

		getEnrollEvent.setData(listEvent);
		getEnrollEvent.setMsg(null);

		return getEnrollEvent;
	}
	
	@Override
	public GetAllEventDtoRes findNotEnrollEvent(String id) throws Exception {
		GetAllEventDtoRes getNotEnrollEvent = new GetAllEventDtoRes();

		List<Event> events = eventDao.findNotEnrollEvent(id);
		List<GetAllEventDtoDataRes> listEvent = new ArrayList<>();

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(event.getId());
			data.setEventCode(event.getEventCode());
			data.setEventTitle(event.getEventTitle());
			data.setEventProvider(event.getEventProvider());
			data.setEventPrice(event.getEventPrice());
			data.setEventTimeStart(event.getEventTimeStart());
			data.setEventTimeEnd(event.getEventTimeEnd());
			data.setEventDateStart(event.getEventDateStart());
			data.setEventDateEnd(event.getEventDateEnd());
			data.setIsApprove(event.getIsApprove());
			data.setCategoryId(event.getCategoryId().getId());
			data.setCategoryName(event.getCategoryId().getCategoryName());
			data.setTypeId(event.getTypeId().getId());
			data.setTypeName(event.getTypeId().getTypeName());
			data.setPriceId(event.getPriceId().getId());
			data.setPriceName(event.getPriceId().getPriceName());
			
			if(event.getAttachmentId() != null) {
				data.setAttachmentId(event.getAttachmentId().getId());
				data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
			}
			
			data.setVersion(event.getVersion());
			data.setIsActive(event.getIsActive());

			listEvent.add(data);
		}

		getNotEnrollEvent.setData(listEvent);
		getNotEnrollEvent.setMsg(null);

		return getNotEnrollEvent;
	}
	
	@Override
	public List<GetReportProfileAttendanceEventDto> getReportEnroll(String eventId) throws Exception {
		List<GetReportProfileAttendanceEventDto> reportEnrolls = new ArrayList<>();
		reportEnrolls = eventDao.getReportEnrolls(eventId);
		return reportEnrolls;
	}
	
	@Override
	public List<GetReportIncomeEventDto> getReportIncome(String eventId) throws Exception {
		List<GetReportIncomeEventDto> reportIncome = new ArrayList<>();
		reportIncome = eventDao.getReportIncome(eventId);
		return reportIncome;
	}
	
}
