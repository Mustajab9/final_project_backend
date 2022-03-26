package com.lawencon.community.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dto.enrolldetail.InsertEnrollDetailDtoReq;
import com.lawencon.community.dto.enrollevent.DeleteByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoRes;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.EnrollEvent;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.community.service.EnrollDetailService;
import com.lawencon.community.service.EnrollEventService;
import com.lawencon.community.dto.enrollevent.GetByUserIdDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetByUserIdDtoRes;

@Service
public class EnrollEventServiceImpl extends BaseService implements EnrollEventService {
	private EnrollEventDao enrollEventDao;
	private PaymentMethodDao paymentMethodDao;
	private AttachmentDao attachmentDao;
	private EnrollDetailService enrollDetailService;

	@Autowired
	public EnrollEventServiceImpl(EnrollEventDao enrollEventDao, PaymentMethodDao paymentMethodDao, AttachmentDao attachmentDao) {
		this.enrollEventDao = enrollEventDao;
		this.paymentMethodDao = paymentMethodDao;
		this.attachmentDao = attachmentDao;
	}
	
	@Autowired
	public void setEnrollDetailService(EnrollDetailService enrollDetailService) {
		this.enrollDetailService = enrollDetailService;
	}
	
	@Override
	public GetAllEnrollEventDtoRes findAll() throws Exception {
		GetAllEnrollEventDtoRes getAll = new GetAllEnrollEventDtoRes();

		List<EnrollEvent> enrollEvents = enrollEventDao.findAll();
		List<GetAllEnrollEventDtoDataRes> listUser = new ArrayList<>();

		for (int i = 0; i < enrollEvents.size(); i++) {
			EnrollEvent enrollEvent = enrollEvents.get(i);
			GetAllEnrollEventDtoDataRes data = new GetAllEnrollEventDtoDataRes();

			data.setId(enrollEvent.getId());
			data.setEnrollCode(enrollEvent.getEnrollCode());
			data.setEnrollInvoice(enrollEvent.getEnrollInvoice());
			data.setIsApprove(enrollEvent.getIsApprove());
			data.setProfileId(enrollEvent.getProfileId().getId());
			data.setProfileName(enrollEvent.getProfileId().getProfileName());
			data.setEmail(enrollEvent.getProfileId().getUserId().getEmail());
			
			if(enrollEvent.getAttachmentId() != null) {
				data.setAttachmentId(enrollEvent.getAttachmentId().getId());
				data.setAttachmentExtension(enrollEvent.getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(enrollEvent.getPaymentId().getId());
			data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
			data.setVersion(enrollEvent.getVersion());
			data.setIsActive(enrollEvent.getIsActive());

			listUser.add(data);
		}

		getAll.setData(listUser);
		getAll.setMsg(null);

		return getAll;
	}
	
	@Override
	public GetByEnrollEventIdDtoRes findById(String id) throws Exception {
		GetByEnrollEventIdDtoRes getById = new GetByEnrollEventIdDtoRes();

		EnrollEvent enrollEvent = enrollEventDao.findById(id);
		GetByEnrollEventIdDtoDataRes data = new GetByEnrollEventIdDtoDataRes();

		data.setId(enrollEvent.getId());
		data.setEnrollCode(enrollEvent.getEnrollCode());
		data.setEnrollInvoice(enrollEvent.getEnrollInvoice());
		data.setIsApprove(enrollEvent.getIsApprove());
		data.setProfileId(enrollEvent.getProfileId().getId());
		data.setProfileName(enrollEvent.getProfileId().getProfileName());
		data.setEmail(enrollEvent.getProfileId().getUserId().getEmail());
		
		if(enrollEvent.getAttachmentId() != null) {
			data.setAttachmentId(enrollEvent.getAttachmentId().getId());
			data.setAttachmentExtension(enrollEvent.getAttachmentId().getAttachmentExtension());
		}
		
		data.setPaymentId(enrollEvent.getPaymentId().getId());
		data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
		data.setVersion(enrollEvent.getVersion());
		data.setIsActive(enrollEvent.getIsActive());
		
		getById.setData(data);
		getById.setMsg(null);

		return getById;
	}
	
	@Override
	public InsertEnrollEventDtoRes insert(String data, MultipartFile file) throws Exception {
		InsertEnrollEventDtoReq enrollEventDto = new ObjectMapper().readValue(data, InsertEnrollEventDtoReq.class);
		InsertEnrollEventDtoRes insert = new InsertEnrollEventDtoRes();

		try {
			EnrollEvent enrollEvent = new EnrollEvent();
			enrollEvent.setEnrollCode(getAlphaNumericString(5));

			PaymentMethod paymentMethod = paymentMethodDao.findById(enrollEventDto.getPaymentId());
			enrollEvent.setPaymentId(paymentMethod);
			
			enrollEvent.setCreatedBy(getId());
			
			if(file != null) {
				Attachment attachment = new Attachment();
				attachment.setAttachmentContent(file.getBytes());
				
				String extension = fileExtensionName(file);
				attachment.setAttachmentExtension(extension);
				
				Attachment attachmentInsert = attachmentDao.save(attachment);
				enrollEvent.setAttachmentId(attachmentInsert);
			}

			begin();
			EnrollEvent enrollEventInsert = enrollEventDao.save(enrollEvent);
			commit();

			InsertEnrollEventDtoDataRes dataDto = new InsertEnrollEventDtoDataRes();
			dataDto.setId(enrollEventInsert.getId());
			
			if(dataDto != null) {
				InsertEnrollDetailDtoReq detailInsert = new InsertEnrollDetailDtoReq();
				detailInsert.setEnrollEventId(enrollEventInsert.getId());
				detailInsert.setEventId(enrollEventDto.getEventId());
				
				enrollDetailService.insert(detailInsert);
			}

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
	public UpdateEnrollEventDtoRes update(UpdateEnrollEventDtoReq data) throws Exception {
		UpdateEnrollEventDtoRes update = new UpdateEnrollEventDtoRes();

		try {
			if (data.getVersion() != null) {
				EnrollEvent enrollEvent = enrollEventDao.findById(data.getId());

				enrollEvent.setIsApprove(data.getIsApprove());
				enrollEvent.setUpdatedBy(getId());
				enrollEvent.setVersion(data.getVersion());

				if (data.getIsActive() != null) {
					enrollEvent.setIsActive(data.getIsActive());
				}

				begin();
				EnrollEvent enrollEventUpdate = enrollEventDao.save(enrollEvent);
				commit();

				UpdateEnrollEventDtoDataRes dataDto = new UpdateEnrollEventDtoDataRes();
				dataDto.setVersion(enrollEventUpdate.getVersion());

				update.setData(dataDto);
				update.setMsg("Update Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return update;
	}
	
	@Override
	public DeleteByEnrollEventIdDtoRes deleteById(String id) throws Exception {
		DeleteByEnrollEventIdDtoRes deleteById = new DeleteByEnrollEventIdDtoRes();

		try {
			begin();
			boolean isDeleted = enrollEventDao.deleteById(id);
			commit();

			if (isDeleted) {
				deleteById.setMsg("Delete Success");
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetByUserIdDtoRes findByUser(String id) throws Exception {
		GetByUserIdDtoRes getByUser = new GetByUserIdDtoRes();

		List<EnrollEvent> enrollEvents = enrollEventDao.findByUser(id);
		List<GetByUserIdDtoDataRes> listEnrollEvent = new ArrayList<>();

		for (int i = 0; i < enrollEvents.size(); i++) {
			EnrollEvent enrollEvent = enrollEvents.get(i);
			GetByUserIdDtoDataRes data = new GetByUserIdDtoDataRes();
			
			data.setId(enrollEvent.getId());
			data.setEnrollCode(enrollEvent.getEnrollCode());
			data.setEnrollInvoice(enrollEvent.getEnrollInvoice());
			data.setIsApprove(enrollEvent.getIsApprove());
			data.setProfileId(enrollEvent.getProfileId().getId());
			data.setProfileName(enrollEvent.getProfileId().getProfileName());
			data.setEmail(enrollEvent.getProfileId().getUserId().getEmail());
			
			if(enrollEvent.getAttachmentId() != null) {
				data.setAttachmentId(enrollEvent.getAttachmentId().getId());
				data.setAttachmentExtension(enrollEvent.getAttachmentId().getAttachmentExtension());
			}
			
			data.setPaymentId(enrollEvent.getPaymentId().getId());
			data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
			data.setVersion(enrollEvent.getVersion());
			data.setIsActive(enrollEvent.getIsActive());
			
			listEnrollEvent.add(data);
		}
		
		getByUser.setData(listEnrollEvent);
		getByUser.setMsg(null);

		return getByUser;
	}
}
