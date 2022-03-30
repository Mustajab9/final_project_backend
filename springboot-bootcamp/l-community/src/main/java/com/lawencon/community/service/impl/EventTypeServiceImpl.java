package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dto.eventtype.DeleteByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.GetAllEventTypeDtoDataRes;
import com.lawencon.community.dto.eventtype.GetAllEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.GetByEventTypeIdDtoDataRes;
import com.lawencon.community.dto.eventtype.GetByEventTypeIdDtoRes;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoDataRes;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.InsertEventTypeDtoRes;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoDataRes;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoReq;
import com.lawencon.community.dto.eventtype.UpdateEventTypeDtoRes;
import com.lawencon.community.model.EventType;
import com.lawencon.community.service.EventTypeService;

@Service
public class EventTypeServiceImpl extends BaseService implements EventTypeService {
	private EventTypeDao typeDao;

	@Autowired
	public EventTypeServiceImpl(EventTypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	@Override
	public GetAllEventTypeDtoRes findAll(int startPage, int maxPage) throws Exception {
		GetAllEventTypeDtoRes getAll = new GetAllEventTypeDtoRes();

		List<EventType> eventTypes = typeDao.findAll(startPage, maxPage);
		List<GetAllEventTypeDtoDataRes> listEventType = new ArrayList<>();

		for (int i = 0; i < eventTypes.size(); i++) {
			EventType eventType = eventTypes.get(i);
			GetAllEventTypeDtoDataRes data = new GetAllEventTypeDtoDataRes();

			data.setId(eventType.getId());
			data.setTypeName(data.getTypeName());
			data.setTypeCode(data.getTypeCode());
			data.setVersion(eventType.getVersion());
			data.setIsActive(eventType.getIsActive());

			listEventType.add(data);
		}

		getAll.setData(listEventType);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByEventTypeIdDtoRes findById(String id) throws Exception {
		GetByEventTypeIdDtoRes getById = new GetByEventTypeIdDtoRes();

		EventType eventType = typeDao.findById(id);
		GetByEventTypeIdDtoDataRes data = new GetByEventTypeIdDtoDataRes();

		data.setId(eventType.getId());
		data.setTypeName(data.getTypeName());
		data.setTypeCode(data.getTypeCode());
		data.setVersion(eventType.getVersion());
		data.setIsActive(eventType.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertEventTypeDtoRes insert(InsertEventTypeDtoReq data) throws Exception {
		InsertEventTypeDtoRes insert = new InsertEventTypeDtoRes();

		try {
			EventType eventType = new EventType();
			eventType.setTypeCode(getAlphaNumericString(5));
			eventType.setTypeName(data.getTypeName());
			eventType.setCreatedBy(getId());
			
			begin();
			EventType eventTypeInsert = typeDao.save(eventType);
			commit();

			InsertEventTypeDtoDataRes dataDto = new InsertEventTypeDtoDataRes();
			dataDto.setId(eventTypeInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(CommonConstant.ACTION_ADD.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Event Type " + CommonConstant.HAS_BEEN_ADDED.getDetail());
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdateEventTypeDtoRes update(UpdateEventTypeDtoReq data) throws Exception {
		UpdateEventTypeDtoRes update = new UpdateEventTypeDtoRes();

		try {
			if (data.getVersion() != null) {
				EventType eventType = typeDao.findById(data.getId());
				eventType.setTypeName(data.getTypeName());
				eventType.setUpdatedBy(getId());
				eventType.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					eventType.setIsActive(data.getIsActive());
				}

				begin();
				EventType eventTypeUpdate = typeDao.save(eventType);
				commit();

				UpdateEventTypeDtoDataRes dataDto = new UpdateEventTypeDtoDataRes();
				dataDto.setVersion(eventTypeUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Event Type " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByEventTypeIdDtoRes deleteById(String id) throws Exception {
		DeleteByEventTypeIdDtoRes deleteById = new DeleteByEventTypeIdDtoRes();

		try {
			begin();
			boolean isDeleted = typeDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Event Type " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
}
