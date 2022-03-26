package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.EnrollDetailDao;
import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoDataRes;
import com.lawencon.community.dto.enrolldetail.GetAllEnrollDetailDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoDataRes;
import com.lawencon.community.dto.enrolldetail.GetByEnrollDetailIdDtoRes;
import com.lawencon.community.dto.enrolldetail.GetByEventIdDtoDataRes;
import com.lawencon.community.dto.enrolldetail.GetByEventIdDtoRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoDataRes;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoRes;
import com.lawencon.community.model.EnrollDetail;
import com.lawencon.community.model.EnrollEvent;
import com.lawencon.community.model.Event;
import com.lawencon.community.service.EnrollDetailService;

@Service
public class EnrollDetailServiceImpl extends BaseService implements EnrollDetailService  {
	private EnrollDetailDao enrollDetailDao;
	private EnrollEventDao enrollEventDao;
	private EventDao eventDao;

	@Autowired
	public EnrollDetailServiceImpl(EnrollDetailDao enrollDetailDao, EnrollEventDao enrollEventDao, EventDao eventDao) {
		this.enrollDetailDao = enrollDetailDao;
		this.enrollEventDao = enrollEventDao;
		this.eventDao = eventDao;
	}
	
	@Override
	public GetAllEnrollDetailDtoRes findAll() throws Exception {
		GetAllEnrollDetailDtoRes getAll = new GetAllEnrollDetailDtoRes();

		List<EnrollDetail> enrollDetails = enrollDetailDao.findAll();
		List<GetAllEnrollDetailDtoDataRes> listEnrollDetail = new ArrayList<>();

		for (int i = 0; i < enrollDetails.size(); i++) {
			EnrollDetail enrollDetail = enrollDetails.get(i);
			GetAllEnrollDetailDtoDataRes data = new GetAllEnrollDetailDtoDataRes();

			data.setId(enrollDetail.getId());
			data.setEventId(enrollDetail.getEventId().getId());
			data.setEventCode(enrollDetail.getEventId().getEventCode());
			data.setEventTitle(enrollDetail.getEventId().getEventTitle());
			data.setEventProvider(enrollDetail.getEventId().getEventProvider());
			data.setEventPrice(enrollDetail.getEventId().getEventPrice());
			data.setEventTimeStart(enrollDetail.getEventId().getEventTimeStart());
			data.setEventTimeEnd(enrollDetail.getEventId().getEventTimeEnd());
			data.setEventDateStart(enrollDetail.getEventId().getEventDateStart());
			data.setEventDateEnd(enrollDetail.getEventId().getEventDateEnd());
			data.setIsEventApprove(enrollDetail.getEventId().getIsApprove());
			data.setCategoryId(enrollDetail.getEventId().getCategoryId().getId());
			data.setCategoryName(enrollDetail.getEventId().getCategoryId().getCategoryName());
			data.setTypeId(enrollDetail.getEventId().getTypeId().getId());
			data.setTypeName(enrollDetail.getEventId().getTypeId().getTypeName());
			data.setPriceId(enrollDetail.getEventId().getPriceId().getId());
			data.setPriceName(enrollDetail.getEventId().getPriceId().getPriceName());
			
			if(enrollDetail.getEventId().getAttachmentId() != null) {
				data.setAttachmentEventId(enrollDetail.getEventId().getAttachmentId().getId());
				data.setAttachmentEventExtension(enrollDetail.getEventId().getAttachmentId().getAttachmentExtension());
			}
			
			data.setEnrollId(enrollDetail.getEnrollId().getId());
			data.setEnrollCode(enrollDetail.getEnrollId().getEnrollCode());
			data.setEnrollInvoice(enrollDetail.getEnrollId().getEnrollInvoice());
			data.setIsEnrollApprove(enrollDetail.getEnrollId().getIsApprove());
			data.setProfileId(enrollDetail.getEnrollId().getProfileId().getId());
			data.setProfileName(enrollDetail.getEnrollId().getProfileId().getProfileName());
			data.setEmail(enrollDetail.getEnrollId().getProfileId().getUserId().getEmail());
			
			if(enrollDetail.getEnrollId().getAttachmentId() != null) {
				data.setAttachmentEnrollId(enrollDetail.getEnrollId().getAttachmentId().getId());
				data.setAttachmentEnrollExtension(enrollDetail.getEnrollId().getAttachmentId().getAttachmentExtension());				
			}
			
			data.setPaymentId(enrollDetail.getEnrollId().getPaymentId().getId());
			data.setPaymentName(enrollDetail.getEnrollId().getPaymentId().getPaymentName());
			data.setVersion(enrollDetail.getVersion());
			data.setIsActive(enrollDetail.getIsActive());

			listEnrollDetail.add(data);
		}

		getAll.setData(listEnrollDetail);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByEnrollDetailIdDtoRes findById(String id) throws Exception {
		GetByEnrollDetailIdDtoRes getById = new GetByEnrollDetailIdDtoRes();

		EnrollDetail enrollDetail = enrollDetailDao.findById(id);
		GetByEnrollDetailIdDtoDataRes data = new GetByEnrollDetailIdDtoDataRes();

		data.setEventId(enrollDetail.getEventId().getId());
		data.setEventCode(enrollDetail.getEventId().getEventCode());
		data.setEventTitle(enrollDetail.getEventId().getEventTitle());
		data.setEventProvider(enrollDetail.getEventId().getEventProvider());
		data.setEventPrice(enrollDetail.getEventId().getEventPrice());
		data.setEventTimeStart(enrollDetail.getEventId().getEventTimeStart());
		data.setEventTimeEnd(enrollDetail.getEventId().getEventTimeEnd());
		data.setEventDateStart(enrollDetail.getEventId().getEventDateStart());
		data.setEventDateEnd(enrollDetail.getEventId().getEventDateEnd());
		data.setIsEventApprove(enrollDetail.getEventId().getIsApprove());
		data.setCategoryId(enrollDetail.getEventId().getCategoryId().getId());
		data.setCategoryName(enrollDetail.getEventId().getCategoryId().getCategoryName());
		data.setTypeId(enrollDetail.getEventId().getTypeId().getId());
		data.setTypeName(enrollDetail.getEventId().getTypeId().getTypeName());
		data.setPriceId(enrollDetail.getEventId().getPriceId().getId());
		data.setPriceName(enrollDetail.getEventId().getPriceId().getPriceName());
		
		if(enrollDetail.getEventId().getAttachmentId() != null) {
			data.setAttachmentEventId(enrollDetail.getEventId().getAttachmentId().getId());
			data.setAttachmentEventExtension(enrollDetail.getEventId().getAttachmentId().getAttachmentExtension());
		}
		
		data.setEnrollId(enrollDetail.getEnrollId().getId());
		data.setEnrollCode(enrollDetail.getEnrollId().getEnrollCode());
		data.setEnrollInvoice(enrollDetail.getEnrollId().getEnrollInvoice());
		data.setIsEnrollApprove(enrollDetail.getEnrollId().getIsApprove());
		data.setProfileId(enrollDetail.getEnrollId().getProfileId().getId());
		data.setProfileName(enrollDetail.getEnrollId().getProfileId().getProfileName());
		data.setEmail(enrollDetail.getEnrollId().getProfileId().getUserId().getEmail());
		
		if(enrollDetail.getEnrollId().getAttachmentId() != null) {
			data.setAttachmentEnrollId(enrollDetail.getEnrollId().getAttachmentId().getId());
			data.setAttachmentEnrollExtension(enrollDetail.getEnrollId().getAttachmentId().getAttachmentExtension());				
		}
		
		data.setPaymentId(enrollDetail.getEnrollId().getPaymentId().getId());
		data.setPaymentName(enrollDetail.getEnrollId().getPaymentId().getPaymentName());
		data.setVersion(enrollDetail.getVersion());
		data.setIsActive(enrollDetail.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertEnrollDetailDtoRes insert(InsertEnrollDetailDtoReq data) throws Exception {
		InsertEnrollDetailDtoRes insert = new InsertEnrollDetailDtoRes();

		try {
			EnrollDetail enrollDetail = new EnrollDetail();
			
			EnrollEvent enrollEvent = enrollEventDao.findById(data.getEnrollEventId());
			enrollDetail.setEnrollId(enrollEvent);
			
			Event event = eventDao.findById(data.getEventId());
			enrollDetail.setEventId(event);
			
			begin();
			EnrollDetail enrollDetailInsert = enrollDetailDao.save(enrollDetail);
			commit();
			
			InsertEnrollDetailDtoDataRes dataDto = new InsertEnrollDetailDtoDataRes();
			dataDto.setId(enrollDetailInsert.getId());

			insert.setData(dataDto);
			insert.setMsg("Insert Success");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public GetByEventIdDtoRes findByEvent(String id) throws Exception {
		GetByEventIdDtoRes getByEvent = new GetByEventIdDtoRes();
		
		List<EnrollDetail> enrollDetails = enrollDetailDao.findByEvent(id);
		List<GetByEventIdDtoDataRes> listEnrollDetail = new ArrayList<>();
		
		for (int i = 0; i < enrollDetails.size(); i++) {
			EnrollDetail enrollDetail = enrollDetails.get(i);
			GetByEventIdDtoDataRes data = new GetByEventIdDtoDataRes();

			data.setId(enrollDetail.getId());
			data.setEventId(enrollDetail.getEventId().getId());
			data.setEventCode(enrollDetail.getEventId().getEventCode());
			data.setEventTitle(enrollDetail.getEventId().getEventTitle());
			data.setEventProvider(enrollDetail.getEventId().getEventProvider());
			data.setEventPrice(enrollDetail.getEventId().getEventPrice());
			data.setEventTimeStart(enrollDetail.getEventId().getEventTimeStart());
			data.setEventTimeEnd(enrollDetail.getEventId().getEventTimeEnd());
			data.setEventDateStart(enrollDetail.getEventId().getEventDateStart());
			data.setEventDateEnd(enrollDetail.getEventId().getEventDateEnd());
			data.setIsEventApprove(enrollDetail.getEventId().getIsApprove());
			data.setCategoryId(enrollDetail.getEventId().getCategoryId().getId());
			data.setCategoryName(enrollDetail.getEventId().getCategoryId().getCategoryName());
			data.setTypeId(enrollDetail.getEventId().getTypeId().getId());
			data.setTypeName(enrollDetail.getEventId().getTypeId().getTypeName());
			data.setPriceId(enrollDetail.getEventId().getPriceId().getId());
			data.setPriceName(enrollDetail.getEventId().getPriceId().getPriceName());
			
			if(enrollDetail.getEventId().getAttachmentId() != null) {
				data.setAttachmentEventId(enrollDetail.getEventId().getAttachmentId().getId());
				data.setAttachmentEventExtension(enrollDetail.getEventId().getAttachmentId().getAttachmentExtension());
			}
			
			data.setEnrollId(enrollDetail.getEnrollId().getId());
			data.setEnrollCode(enrollDetail.getEnrollId().getEnrollCode());
			data.setEnrollInvoice(enrollDetail.getEnrollId().getEnrollInvoice());
			data.setIsEnrollApprove(enrollDetail.getEnrollId().getIsApprove());
			data.setProfileId(enrollDetail.getEnrollId().getProfileId().getId());
			data.setProfileName(enrollDetail.getEnrollId().getProfileId().getProfileName());
			data.setEmail(enrollDetail.getEnrollId().getProfileId().getUserId().getEmail());
			
			if(enrollDetail.getEnrollId().getAttachmentId() != null) {
				data.setAttachmentEnrollId(enrollDetail.getEnrollId().getAttachmentId().getId());
				data.setAttachmentEnrollExtension(enrollDetail.getEnrollId().getAttachmentId().getAttachmentExtension());				
			}
			
			data.setPaymentId(enrollDetail.getEnrollId().getPaymentId().getId());
			data.setPaymentName(enrollDetail.getEnrollId().getPaymentId().getPaymentName());
			data.setVersion(enrollDetail.getVersion());
			data.setIsActive(enrollDetail.getIsActive());

			listEnrollDetail.add(data);
		}

		getByEvent.setData(listEnrollDetail);
		getByEvent.setMsg(null);

		return getByEvent;
	}
}
