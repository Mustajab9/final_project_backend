package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.PaymentEventDao;
import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dto.paymentevent.DeleteByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetAllPaymentEventDtoDataRes;
import com.lawencon.community.dto.paymentevent.GetAllPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.GetByPaymentEventIdDtoDataRes;
import com.lawencon.community.dto.paymentevent.GetByPaymentEventIdDtoRes;
import com.lawencon.community.dto.paymentevent.GetPaymentEventByUserDtoDataRes;
import com.lawencon.community.dto.paymentevent.GetPaymentEventByUserDtoRes;
import com.lawencon.community.dto.paymentevent.InsertPaymentEventDtoDataRes;
import com.lawencon.community.dto.paymentevent.InsertPaymentEventDtoReq;
import com.lawencon.community.dto.paymentevent.InsertPaymentEventDtoRes;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoDataRes;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoReq;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoRes;
import com.lawencon.community.dto.paymenteventdetail.InsertPaymentEventDetailDtoReq;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.PaymentEvent;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.community.service.PaymentEventDetailService;
import com.lawencon.community.service.PaymentEventService;

@Service
public class PaymentEventServiceImpl extends BaseService implements PaymentEventService {
	private PaymentEventDao paymentEventDao;
	private PaymentMethodDao paymentMethodDao;
	private AttachmentDao attachmentDao;
	private PaymentEventDetailService paymentEventDetailService;

	@Autowired
	public PaymentEventServiceImpl(PaymentEventDao paymentEventDao, PaymentMethodDao paymentMethodDao, AttachmentDao attachmentDao) {
		this.paymentEventDao = paymentEventDao;
		this.paymentMethodDao = paymentMethodDao;
		this.attachmentDao = attachmentDao;
	}
	
	@Autowired
	public void setPaymentEventDetailService(PaymentEventDetailService paymentEventDetailService) {
		this.paymentEventDetailService = paymentEventDetailService;
	}
	
	@Override
	public GetAllPaymentEventDtoRes findAll() throws Exception {
		GetAllPaymentEventDtoRes getAll = new GetAllPaymentEventDtoRes();

		List<PaymentEvent> paymentEvents = paymentEventDao.findAll();
		List<GetAllPaymentEventDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < paymentEvents.size(); i++) {
			PaymentEvent paymentEvent = paymentEvents.get(i);
			GetAllPaymentEventDtoDataRes data = new GetAllPaymentEventDtoDataRes();

			data.setId(paymentEvent.getId());
			data.setPaymentEventCode(paymentEvent.getPaymentEventCode());
			data.setPaymentEventInvoice(paymentEvent.getPaymentEventInvoice());
			data.setIsApprove(paymentEvent.getIsApprove());
			
			if(paymentEvent.getAttachmentId() != null) {
				data.setAttachmentId(paymentEvent.getAttachmentId().getId());
				data.setAttachmentExtension(paymentEvent.getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(paymentEvent.getPaymentId().getId());
			data.setPaymentName(paymentEvent.getPaymentId().getPaymentName());
			data.setVersion(paymentEvent.getVersion());
			data.setIsActive(paymentEvent.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByPaymentEventIdDtoRes findById(String id) throws Exception {
		GetByPaymentEventIdDtoRes getById = new GetByPaymentEventIdDtoRes();

		PaymentEvent paymentEvent = paymentEventDao.findById(id);
		GetByPaymentEventIdDtoDataRes data = new GetByPaymentEventIdDtoDataRes();

		data.setId(paymentEvent.getId());
		data.setPaymentEventCode(paymentEvent.getPaymentEventCode());
		data.setPaymentEventInvoice(paymentEvent.getPaymentEventInvoice());
		data.setIsApprove(paymentEvent.getIsApprove());
		
		if(paymentEvent.getAttachmentId() != null) {
			data.setAttachmentId(paymentEvent.getAttachmentId().getId());
			data.setAttachmentExtension(paymentEvent.getAttachmentId().getAttachmentExtension());
		}
		
		data.setPaymentId(paymentEvent.getPaymentId().getId());
		data.setPaymentName(paymentEvent.getPaymentId().getPaymentName());
		data.setVersion(paymentEvent.getVersion());
		data.setIsActive(paymentEvent.getIsActive());
		
		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertPaymentEventDtoRes insert(String data, MultipartFile file) throws Exception {
		InsertPaymentEventDtoReq paymentEventDto = new ObjectMapper().readValue(data, InsertPaymentEventDtoReq.class);
		InsertPaymentEventDtoRes insert = new InsertPaymentEventDtoRes();

		try {
			PaymentEvent paymentEvent = new PaymentEvent();
			paymentEvent.setPaymentEventCode(getAlphaNumericString(5));

			PaymentMethod paymentMethod = paymentMethodDao.findById(paymentEventDto.getPaymentId());
			paymentEvent.setPaymentId(paymentMethod);
			
			paymentEvent.setCreatedBy(getId());
			
			begin();
			if(file != null) {
				Attachment attachment = new Attachment();
				attachment.setAttachmentCode(getAlphaNumericString(5));
				attachment.setAttachmentContent(file.getBytes());
				
				String extension = fileExtensionName(file);
				attachment.setAttachmentExtension(extension);
				attachment.setCreatedBy(getId());
				
				Attachment attachmentInsert = attachmentDao.save(attachment);
				paymentEvent.setAttachmentId(attachmentInsert);
			}
		
			PaymentEvent paymentEventInsert = paymentEventDao.save(paymentEvent);		

			InsertPaymentEventDtoDataRes dataDto = new InsertPaymentEventDtoDataRes();
			dataDto.setId(paymentEventInsert.getId());
			
			if(paymentEventInsert != null) {
				InsertPaymentEventDetailDtoReq detailInsert = new InsertPaymentEventDetailDtoReq();
				
				List<String> listEvent = paymentEventDto.getEventId();
				
				for (int i = 0; i < listEvent.size(); i++) {
					detailInsert.setPaymentEventId(paymentEventInsert.getId());
					detailInsert.setEventId(listEvent.get(i));
					
					paymentEventDetailService.insert(detailInsert);
				}
			}
			commit();

			insert.setData(dataDto);
			insert.setMsg("You " + CommonConstant.SUCCESS.getDetail() + " Payment Event");
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return insert;
	}
	
	@Override
	public UpdatePaymentEventDtoRes update(UpdatePaymentEventDtoReq data) throws Exception {
		UpdatePaymentEventDtoRes update = new UpdatePaymentEventDtoRes();

		try {
			if (data.getVersion() != null) {
				PaymentEvent paymentEvent = paymentEventDao.findById(data.getId());

				paymentEvent.setPaymentEventInvoice(data.getInvoice());
				paymentEvent.setIsApprove(data.getIsApprove());
				paymentEvent.setUpdatedBy(getId());
				paymentEvent.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					paymentEvent.setIsActive(data.getIsActive());
				}

				begin();
				PaymentEvent paymentEventUpdate = paymentEventDao.save(paymentEvent);
				commit();

				UpdatePaymentEventDtoDataRes dataDto = new UpdatePaymentEventDtoDataRes();
				dataDto.setVersion(paymentEventUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Payment Event " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByPaymentEventIdDtoRes deleteById(String id) throws Exception {
		DeleteByPaymentEventIdDtoRes deleteById = new DeleteByPaymentEventIdDtoRes();

		try {
			begin();
			boolean isDeleted = paymentEventDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Payment Event " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetPaymentEventByUserDtoRes findByUser(String id) throws Exception {
		GetPaymentEventByUserDtoRes getByUser = new GetPaymentEventByUserDtoRes();

		List<PaymentEvent> paymentEvents = paymentEventDao.findByUser(id);
		List<GetPaymentEventByUserDtoDataRes> listPaymentEvent = new ArrayList<>();

		for (int i = 0; i < paymentEvents.size(); i++) {
			PaymentEvent paymentEvent = paymentEvents.get(i);
			GetPaymentEventByUserDtoDataRes data = new GetPaymentEventByUserDtoDataRes();
			
			data.setId(paymentEvent.getId());
			data.setPaymentEventCode(paymentEvent.getPaymentEventCode());
			data.setPaymentEventInvoice(paymentEvent.getPaymentEventInvoice());
			data.setIsApprove(paymentEvent.getIsApprove());
			
			if(paymentEvent.getAttachmentId() != null) {
				data.setAttachmentId(paymentEvent.getAttachmentId().getId());
				data.setAttachmentExtension(paymentEvent.getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(paymentEvent.getPaymentId().getId());
			data.setPaymentName(paymentEvent.getPaymentId().getPaymentName());
			data.setVersion(paymentEvent.getVersion());
			data.setIsActive(paymentEvent.getIsActive());
			
			listPaymentEvent.add(data);
		}
		
		getByUser.setData(listPaymentEvent);
		getByUser.setMsg(null);

		return getByUser;
	}
}
