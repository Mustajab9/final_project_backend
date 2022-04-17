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
import com.lawencon.model.SearchQuery;

@Service
public class EventTypeServiceImpl extends BaseService implements EventTypeService {
	private EventTypeDao typeDao;

	@Autowired
	public EventTypeServiceImpl(EventTypeDao typeDao) {
		this.typeDao = typeDao;
	}
	
	@Override
	public GetAllEventTypeDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllEventTypeDtoRes getAll = new GetAllEventTypeDtoRes();

		SearchQuery<EventType> eventTypes = typeDao.findAll(query, startPage, maxPage);
		List<GetAllEventTypeDtoDataRes> listEventType = new ArrayList<>();

		for (int i = 0; i < eventTypes.getData().size(); i++) {
			EventType eventType = eventTypes.getData().get(i);
			GetAllEventTypeDtoDataRes data = new GetAllEventTypeDtoDataRes();

			data.setId(eventType.getId());
			data.setTypeName(eventType.getTypeName());
			data.setTypeCode(eventType.getTypeCode());
			data.setVersion(eventType.getVersion());
			data.setIsActive(eventType.getIsActive());

			listEventType.add(data);
		}

		getAll.setData(listEventType);
		getAll.setMsg(null);
		getAll.setTotal(eventTypes.getCount());

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
			validateInsert(data);
			
			EventType eventType = new EventType();
			eventType.setTypeCode(data.getTypeCode());
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
			validateUpdate(data);
			
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
			validateDelete(id);
			
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
	
	private void validateInsert(InsertEventTypeDtoReq data) throws Exception {
		if (data.getTypeCode() == null || data.getTypeCode().trim().equals("")) {
			throw new Exception("Type Code Cant Null");
		} else {
			EventType type = typeDao.findByCode(data.getTypeCode());
			if (type != null) {
				throw new Exception("Type Code Already Exsist");
			}
			if (data.getTypeName() == null || data.getTypeName().trim().equals("")) {
				throw new Exception("Type Name Cant Null");
			}
		}
	}

	private void validateUpdate(UpdateEventTypeDtoReq data) throws Exception {
		if (data.getId() == null || data.getId().trim().equals("")) {
			throw new Exception("Type Id Cant Null");
		} else {
			EventType type = typeDao.findById(data.getId());
			if (data.getTypeName() == null || data.getTypeName().trim().equals("")) {
				throw new Exception("Type Name Cant Null");
			}
			if (type.getVersion() != data.getVersion()) {
				throw new Exception("Type You Update Already Update By Someone");
			}
		}
	}

	private void validateDelete(String id) throws Exception {
		EventType type = typeDao.findById(id);
		
		if(type == null) {
			throw new Exception("Type Id Not Exsist");
		}
	}
}
