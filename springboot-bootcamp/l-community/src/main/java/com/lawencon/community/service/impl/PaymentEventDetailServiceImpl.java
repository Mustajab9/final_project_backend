package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dao.PaymentEventDao;
import com.lawencon.community.dao.PaymentEventDetailDao;
import com.lawencon.community.dto.paymenteventdetail.GetAllPaymentEventDetailDtoDataRes;
import com.lawencon.community.dto.paymenteventdetail.GetAllPaymentEventDetailDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetByPaymentEventDetailIdDtoDataRes;
import com.lawencon.community.dto.paymenteventdetail.GetByPaymentEventDetailIdDtoRes;
import com.lawencon.community.dto.paymenteventdetail.GetPaymentEventDetailByEventDtoDataRes;
import com.lawencon.community.dto.paymenteventdetail.GetPaymentEventDetailByEventDtoRes;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoDataRes;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoReq;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoRes;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.PaymentEvent;
import com.lawencon.community.model.PaymentEventDetail;
import com.lawencon.community.service.PaymentEventDetailService;

@Service
public class PaymentEventDetailServiceImpl extends BaseService implements PaymentEventDetailService  {
	private PaymentEventDetailDao paymentDetailDao;
	private PaymentEventDao paymentEventDao;
	private EventDao eventDao;

	@Autowired
	public PaymentEventDetailServiceImpl(PaymentEventDetailDao paymentDetailDao, PaymentEventDao paymentEventDao, EventDao eventDao) {
		this.paymentDetailDao = paymentDetailDao;
		this.paymentEventDao = paymentEventDao;
		this.eventDao = eventDao;
	}
	
	@Override
	public GetAllPaymentEventDetailDtoRes findAll() throws Exception {
		GetAllPaymentEventDetailDtoRes getAll = new GetAllPaymentEventDetailDtoRes();

		List<PaymentEventDetail> paymentDetails = paymentDetailDao.findAll();
		List<GetAllPaymentEventDetailDtoDataRes> listPaymentDetail = new ArrayList<>();

		for (int i = 0; i < paymentDetails.size(); i++) {
			PaymentEventDetail paymentDetail = paymentDetails.get(i);
			GetAllPaymentEventDetailDtoDataRes data = new GetAllPaymentEventDetailDtoDataRes();

			data.setId(paymentDetail.getId());
			data.setEventId(paymentDetail.getEventId().getId());
			data.setEventCode(paymentDetail.getEventId().getEventCode());
			data.setEventTitle(paymentDetail.getEventId().getEventTitle());
			data.setEventProvider(paymentDetail.getEventId().getEventProvider());
			data.setEventPrice(paymentDetail.getEventId().getEventPrice());
			data.setEventTimeStart(paymentDetail.getEventId().getEventTimeStart());
			data.setEventTimeEnd(paymentDetail.getEventId().getEventTimeEnd());
			data.setEventDateStart(paymentDetail.getEventId().getEventDateStart());
			data.setEventDateEnd(paymentDetail.getEventId().getEventDateEnd());
			data.setIsEventApprove(paymentDetail.getEventId().getIsApprove());
			data.setCategoryId(paymentDetail.getEventId().getCategoryId().getId());
			data.setCategoryName(paymentDetail.getEventId().getCategoryId().getCategoryName());
			data.setTypeId(paymentDetail.getEventId().getTypeId().getId());
			data.setTypeName(paymentDetail.getEventId().getTypeId().getTypeName());
			data.setPriceId(paymentDetail.getEventId().getPriceId().getId());
			data.setPriceName(paymentDetail.getEventId().getPriceId().getPriceName());
			
			if(paymentDetail.getEventId().getAttachmentId() != null) {
				data.setAttachmentEventId(paymentDetail.getEventId().getAttachmentId().getId());
				data.setAttachmentEventExtension(paymentDetail.getEventId().getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(paymentDetail.getPaymentId().getId());
			data.setPaymentEventCode(paymentDetail.getPaymentId().getPaymentEventCode());
			data.setPaymentEventInvoice(paymentDetail.getPaymentId().getPaymentEventInvoice());
			data.setIspaymentEventApprove(paymentDetail.getPaymentId().getIsApprove());
			
			if(paymentDetail.getPaymentId().getAttachmentId() != null) {
				data.setAttachmentpaymentEventId(paymentDetail.getPaymentId().getAttachmentId().getId());
				data.setAttachmentpaymentEventExtension(paymentDetail.getPaymentId().getAttachmentId().getAttachmentExtension());				
			}
			
			data.setPaymentId(paymentDetail.getPaymentId().getPaymentId().getId());
			data.setPaymentName(paymentDetail.getPaymentId().getPaymentId().getPaymentName());
			data.setVersion(paymentDetail.getVersion());
			data.setIsActive(paymentDetail.getIsActive());

			listPaymentDetail.add(data);
		}

		getAll.setData(listPaymentDetail);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByPaymentEventDetailIdDtoRes findById(String id) throws Exception {
		GetByPaymentEventDetailIdDtoRes getById = new GetByPaymentEventDetailIdDtoRes();

		PaymentEventDetail paymentDetail = paymentDetailDao.findById(id);
		GetByPaymentEventDetailIdDtoDataRes data = new GetByPaymentEventDetailIdDtoDataRes();

		data.setId(paymentDetail.getId());
		data.setEventId(paymentDetail.getEventId().getId());
		data.setEventCode(paymentDetail.getEventId().getEventCode());
		data.setEventTitle(paymentDetail.getEventId().getEventTitle());
		data.setEventProvider(paymentDetail.getEventId().getEventProvider());
		data.setEventPrice(paymentDetail.getEventId().getEventPrice());
		data.setEventTimeStart(paymentDetail.getEventId().getEventTimeStart());
		data.setEventTimeEnd(paymentDetail.getEventId().getEventTimeEnd());
		data.setEventDateStart(paymentDetail.getEventId().getEventDateStart());
		data.setEventDateEnd(paymentDetail.getEventId().getEventDateEnd());
		data.setIsEventApprove(paymentDetail.getEventId().getIsApprove());
		data.setCategoryId(paymentDetail.getEventId().getCategoryId().getId());
		data.setCategoryName(paymentDetail.getEventId().getCategoryId().getCategoryName());
		data.setTypeId(paymentDetail.getEventId().getTypeId().getId());
		data.setTypeName(paymentDetail.getEventId().getTypeId().getTypeName());
		data.setPriceId(paymentDetail.getEventId().getPriceId().getId());
		data.setPriceName(paymentDetail.getEventId().getPriceId().getPriceName());
		
		if(paymentDetail.getEventId().getAttachmentId() != null) {
			data.setAttachmentEventId(paymentDetail.getEventId().getAttachmentId().getId());
			data.setAttachmentEventExtension(paymentDetail.getEventId().getAttachmentId().getAttachmentExtension());
		}
		
		data.setPaymentId(paymentDetail.getPaymentId().getId());
		data.setPaymentEventCode(paymentDetail.getPaymentId().getPaymentEventCode());
		data.setPaymentEventInvoice(paymentDetail.getPaymentId().getPaymentEventInvoice());
		data.setIspaymentEventApprove(paymentDetail.getPaymentId().getIsApprove());
		
		if(paymentDetail.getPaymentId().getAttachmentId() != null) {
			data.setAttachmentpaymentEventId(paymentDetail.getPaymentId().getAttachmentId().getId());
			data.setAttachmentpaymentEventExtension(paymentDetail.getPaymentId().getAttachmentId().getAttachmentExtension());				
		}
		
		data.setPaymentId(paymentDetail.getPaymentId().getPaymentId().getId());
		data.setPaymentName(paymentDetail.getPaymentId().getPaymentId().getPaymentName());
		data.setVersion(paymentDetail.getVersion());
		data.setIsActive(paymentDetail.getIsActive());

		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPaymentEventDetailDtoRes insert(InsertPaymentEventDetailDtoReq data) throws Exception {
		InsertPaymentEventDetailDtoRes insert = new InsertPaymentEventDetailDtoRes();

		try {
			PaymentEventDetail paymentDetail = new PaymentEventDetail();
			
			PaymentEvent paymentEvent = paymentEventDao.findById(data.getPaymentEventId());
			paymentDetail.setPaymentId(paymentEvent);
			
			Event event = eventDao.findById(data.getEventId());
			paymentDetail.setEventId(event);
			paymentDetail.setCreatedBy(getId());
			
			begin();
			PaymentEventDetail paymentDetailInsert = paymentDetailDao.save(paymentDetail);
			commit();
			
			InsertPaymentEventDetailDtoDataRes dataDto = new InsertPaymentEventDetailDtoDataRes();
			dataDto.setId(paymentDetailInsert.getId());

			insert.setData(dataDto);
			insert.setMsg(null);
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public GetPaymentEventDetailByEventDtoRes findByEvent(String id) throws Exception {
		GetPaymentEventDetailByEventDtoRes getByEvent = new GetPaymentEventDetailByEventDtoRes();
		
		List<PaymentEventDetail> paymentDetails = paymentDetailDao.findByEvent(id);
		List<GetPaymentEventDetailByEventDtoDataRes> listPaymentDetail = new ArrayList<>();
		
		for (int i = 0; i < paymentDetails.size(); i++) {
			PaymentEventDetail paymentDetail = paymentDetails.get(i);
			GetPaymentEventDetailByEventDtoDataRes data = new GetPaymentEventDetailByEventDtoDataRes();

			data.setId(paymentDetail.getId());
			data.setEventId(paymentDetail.getEventId().getId());
			data.setEventCode(paymentDetail.getEventId().getEventCode());
			data.setEventTitle(paymentDetail.getEventId().getEventTitle());
			data.setEventProvider(paymentDetail.getEventId().getEventProvider());
			data.setEventPrice(paymentDetail.getEventId().getEventPrice());
			data.setEventTimeStart(paymentDetail.getEventId().getEventTimeStart());
			data.setEventTimeEnd(paymentDetail.getEventId().getEventTimeEnd());
			data.setEventDateStart(paymentDetail.getEventId().getEventDateStart());
			data.setEventDateEnd(paymentDetail.getEventId().getEventDateEnd());
			data.setIsEventApprove(paymentDetail.getEventId().getIsApprove());
			data.setCategoryId(paymentDetail.getEventId().getCategoryId().getId());
			data.setCategoryName(paymentDetail.getEventId().getCategoryId().getCategoryName());
			data.setTypeId(paymentDetail.getEventId().getTypeId().getId());
			data.setTypeName(paymentDetail.getEventId().getTypeId().getTypeName());
			data.setPriceId(paymentDetail.getEventId().getPriceId().getId());
			data.setPriceName(paymentDetail.getEventId().getPriceId().getPriceName());
			
			if(paymentDetail.getEventId().getAttachmentId() != null) {
				data.setAttachmentEventId(paymentDetail.getEventId().getAttachmentId().getId());
				data.setAttachmentEventExtension(paymentDetail.getEventId().getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(paymentDetail.getPaymentId().getId());
			data.setPaymentEventCode(paymentDetail.getPaymentId().getPaymentEventCode());
			data.setPaymentEventInvoice(paymentDetail.getPaymentId().getPaymentEventInvoice());
			data.setIspaymentEventApprove(paymentDetail.getPaymentId().getIsApprove());
			
			if(paymentDetail.getPaymentId().getAttachmentId() != null) {
				data.setAttachmentpaymentEventId(paymentDetail.getPaymentId().getAttachmentId().getId());
				data.setAttachmentpaymentEventExtension(paymentDetail.getPaymentId().getAttachmentId().getAttachmentExtension());				
			}
			
			data.setPaymentId(paymentDetail.getPaymentId().getPaymentId().getId());
			data.setPaymentName(paymentDetail.getPaymentId().getPaymentId().getPaymentName());
			data.setVersion(paymentDetail.getVersion());
			data.setIsActive(paymentDetail.getIsActive());

			listPaymentDetail.add(data);
		}

		getByEvent.setData(listPaymentDetail);
		getByEvent.setMsg(null);

		return getByEvent;
	}
}
