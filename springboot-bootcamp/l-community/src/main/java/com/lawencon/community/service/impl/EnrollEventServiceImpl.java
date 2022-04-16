package com.lawencon.community.service.impl;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lawencon.community.constant.CommonConstant;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.dao.ProfilesDao;
import com.lawencon.community.dto.enrollevent.DeleteByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetAllEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetByEnrollEventIdDtoRes;
import com.lawencon.community.dto.enrollevent.GetEnrollEventByUserDtoDataRes;
import com.lawencon.community.dto.enrollevent.GetEnrollEventByUserDtoRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.InsertEnrollEventDtoRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoDataRes;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoReq;
import com.lawencon.community.dto.enrollevent.UpdateEnrollEventDtoRes;
import com.lawencon.community.dto.user.EmailDtoReq;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.EnrollEvent;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.community.model.Profiles;
import com.lawencon.community.service.EnrollEventService;

import freemarker.template.Configuration;

@Service
public class EnrollEventServiceImpl extends BaseService implements EnrollEventService {
	private static final String email = "mustajabsa@gmail.com";
	private EnrollEventDao enrollEventDao;
	private PaymentMethodDao paymentMethodDao;
	private AttachmentDao attachmentDao;
	private EventDao eventDao;
	private ProfilesDao profilesDao;
	private JavaMailSender mailSender;

	@Autowired
	public EnrollEventServiceImpl(EnrollEventDao enrollEventDao, PaymentMethodDao paymentMethodDao, AttachmentDao attachmentDao, EventDao eventDao, ProfilesDao profilesDao, JavaMailSender mailSender) {
		this.enrollEventDao = enrollEventDao;
		this.paymentMethodDao = paymentMethodDao;
		this.attachmentDao = attachmentDao;
		this.eventDao = eventDao;
		this.profilesDao = profilesDao;
		this.mailSender = mailSender;
	}
	
	@Autowired
    private Configuration freeMarkerConfiguration;
	
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
			data.setEventId(enrollEvent.getEventId().getId());
			data.setEventCode(enrollEvent.getEventId().getEventCode());
			data.setEventTitle(enrollEvent.getEventId().getEventTitle());
			data.setEventProvider(enrollEvent.getEventId().getEventProvider());
			data.setEventPrice(enrollEvent.getEventId().getEventPrice());
			data.setEventTimeStart(enrollEvent.getEventId().getEventTimeStart());
			data.setEventTimeEnd(enrollEvent.getEventId().getEventTimeEnd());
			data.setEventDateStart(enrollEvent.getEventId().getEventDateStart());
			data.setEventDateEnd(enrollEvent.getEventId().getEventDateEnd());
			data.setIsEventApprove(enrollEvent.getEventId().getIsApprove());
			data.setCategoryId(enrollEvent.getEventId().getCategoryId().getId());
			data.setCategoryName(enrollEvent.getEventId().getCategoryId().getCategoryName());
			data.setTypeId(enrollEvent.getEventId().getTypeId().getId());
			data.setTypeName(enrollEvent.getEventId().getTypeId().getTypeName());
			data.setPriceId(enrollEvent.getEventId().getPriceId().getId());
			data.setPriceName(enrollEvent.getEventId().getPriceId().getPriceName());
			
			if(enrollEvent.getAttachmentId() != null) {
				data.setAttachmentId(enrollEvent.getAttachmentId().getId());
				data.setAttachmentExtension(enrollEvent.getAttachmentId().getAttachmentExtension());
				data.setPaymentId(enrollEvent.getPaymentId().getId());
				data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
			}
			
			data.setCreatedBy(enrollEvent.getCreatedBy());
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
		data.setEventId(enrollEvent.getEventId().getId());
		data.setEventCode(enrollEvent.getEventId().getEventCode());
		data.setEventTitle(enrollEvent.getEventId().getEventTitle());
		data.setEventProvider(enrollEvent.getEventId().getEventProvider());
		data.setEventPrice(enrollEvent.getEventId().getEventPrice());
		data.setEventTimeStart(enrollEvent.getEventId().getEventTimeStart());
		data.setEventTimeEnd(enrollEvent.getEventId().getEventTimeEnd());
		data.setEventDateStart(enrollEvent.getEventId().getEventDateStart());
		data.setEventDateEnd(enrollEvent.getEventId().getEventDateEnd());
		data.setIsEventApprove(enrollEvent.getEventId().getIsApprove());
		data.setCategoryId(enrollEvent.getEventId().getCategoryId().getId());
		data.setCategoryName(enrollEvent.getEventId().getCategoryId().getCategoryName());
		data.setTypeId(enrollEvent.getEventId().getTypeId().getId());
		data.setTypeName(enrollEvent.getEventId().getTypeId().getTypeName());
		data.setPriceId(enrollEvent.getEventId().getPriceId().getId());
		data.setPriceName(enrollEvent.getEventId().getPriceId().getPriceName());
		
		if(enrollEvent.getAttachmentId() != null) {
			data.setAttachmentId(enrollEvent.getAttachmentId().getId());
			data.setAttachmentExtension(enrollEvent.getAttachmentId().getAttachmentExtension());
			data.setPaymentId(enrollEvent.getPaymentId().getId());
			data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
		}
		
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
			
			Profiles profiles = profilesDao.findByUser(getId());
			enrollEvent.setProfileId(profiles);
			
			Event event = eventDao.findById(enrollEventDto.getEventId());
			enrollEvent.setEventId(event);

			PaymentMethod paymentMethod = paymentMethodDao.findById(enrollEventDto.getPaymentId());
			enrollEvent.setPaymentId(paymentMethod);
			
			enrollEvent.setIsApprove(false);
			enrollEvent.setCreatedBy(getId());
			
			begin();
			if(file != null) {
				Attachment attachment = new Attachment();
				attachment.setAttachmentCode(getAlphaNumericString(5));
				attachment.setAttachmentContent(file.getBytes());
				
				String extension = fileExtensionName(file);
				attachment.setAttachmentExtension(extension);
				attachment.setCreatedBy(getId());
				
				Attachment attachmentInsert = attachmentDao.save(attachment);
				enrollEvent.setAttachmentId(attachmentInsert);
			}
			
			EnrollEvent enrollEventInsert = enrollEventDao.save(enrollEvent);
			commit();

			InsertEnrollEventDtoDataRes dataDto = new InsertEnrollEventDtoDataRes();
			dataDto.setId(enrollEventInsert.getId());
			
			insert.setData(dataDto);
			insert.setMsg("You " + CommonConstant.SUCCESS.getDetail() + " Enroll Event");
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
				
				Date date = new Date();
				LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				int month = localDate.getMonthValue();
				String invoice = "IN" + month + "-" + getRandomNumericString(4);
				
				enrollEvent.setEnrollInvoice(invoice);
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
				
				if(enrollEventUpdate.getIsApprove() == true) {
					EnrollEvent invoiceReq = enrollEventDao.findById(data.getId());
					
					invoiceReq.setEnrollInvoice(invoice);
					String subject = "Invoice Number " + invoiceReq.getEventId().getEventTitle();
					
					EmailDtoReq emailReq = new EmailDtoReq();
					emailReq.setTo(invoiceReq.getProfileId().getUserId().getEmail());
					emailReq.setFrom(email);
					emailReq.setSubject(subject);
					Map<String, Object> model = new HashMap<>();
			        model.put("profileName", invoiceReq.getProfileId().getProfileName());
			        model.put("invoice", invoice);
			        model.put("eventTitle", invoiceReq.getEventId().getEventTitle());
			        model.put("eventProvider", invoiceReq.getEventId().getEventProvider());
			        model.put("eventPrice", invoiceReq.getEventId().getEventPrice());
			        model.put("eventDateStart", invoiceReq.getEventId().getEventDateStart());
			        model.put("eventDateEnd", invoiceReq.getEventId().getEventDateEnd());
			        model.put("eventTimeStart", invoiceReq.getEventId().getEventTimeStart());
			        model.put("eventTimeEnd", invoiceReq.getEventId().getEventTimeEnd());
			        emailReq.setModel(model);
			        
			        ExecutorService executor = Executors.newSingleThreadExecutor();

					executor.submit(() -> {
						sendEmail(emailReq);
					});
					executor.shutdown();
				}

				update.setData(dataDto);
				update.setMsg(CommonConstant.ACTION_EDIT.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Enroll Event " + CommonConstant.HAS_BEEN_UPDATED.getDetail());
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
				deleteById.setMsg(CommonConstant.ACTION_DELETE.getDetail() + " " + CommonConstant.SUCCESS.getDetail() + ", Enroll Event " + CommonConstant.HAS_BEEN_DELETED.getDetail());
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollback();
			throw new Exception(e);
		}

		return deleteById;
	}
	
	@Override
	public GetEnrollEventByUserDtoRes findByUser(String id) throws Exception {
		GetEnrollEventByUserDtoRes getByUser = new GetEnrollEventByUserDtoRes();

		List<EnrollEvent> enrollEvents = enrollEventDao.findByUser(id);
		List<GetEnrollEventByUserDtoDataRes> listEnrollEvent = new ArrayList<>();

		for (int i = 0; i < enrollEvents.size(); i++) {
			EnrollEvent enrollEvent = enrollEvents.get(i);
			GetEnrollEventByUserDtoDataRes data = new GetEnrollEventByUserDtoDataRes();
			
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
				data.setPaymentId(enrollEvent.getPaymentId().getId());
				data.setPaymentName(enrollEvent.getPaymentId().getPaymentName());
			}
			
			data.setVersion(enrollEvent.getVersion());
			data.setIsActive(enrollEvent.getIsActive());
			
			listEnrollEvent.add(data);
		}
		
		getByUser.setData(listEnrollEvent);
		getByUser.setMsg(null);

		return getByUser;
	}
	
	public String getRandomNumericString(int n) {
		String randomNumericString = "0123456789";
		StringBuilder sb = new StringBuilder(n);
	
		for (int i = 0; i < n; i++) {
			int index = (int)(randomNumericString.length()* Math.random());
			while(i==0 && index==0) {
				index = (int)(randomNumericString.length()* Math.random());
			}
			sb.append(randomNumericString.charAt(index));
		}
		return sb.toString();
	}
	
	@Async
	public void sendEmail(EmailDtoReq emailReq) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

			messageHelper.setSubject(emailReq.getSubject());
			messageHelper.setFrom(emailReq.getFrom());
			messageHelper.setTo(emailReq.getTo());
			emailReq.setContent(getContentFromTemplate(emailReq.getModel()));
			messageHelper.setText(emailReq.getContent(), true);
			mailSender.send(messageHelper.getMimeMessage());
		}catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getContentFromTemplate(Map<String, Object> model) {
        StringBuffer content = new StringBuffer();

        try {
            content.append(FreeMarkerTemplateUtils
                    .processTemplateIntoString(freeMarkerConfiguration.getTemplate("EventInvoiceEmailTemplate.flth"), model));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
