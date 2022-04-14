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
import com.lawencon.community.constant.EventTypeConstant;
import com.lawencon.community.dao.AttachmentDao;
import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.dao.PaymentEventDao;
import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.dto.event.DeleteByEventIdDtoRes;
import com.lawencon.community.dto.event.GetAllEventDtoDataRes;
import com.lawencon.community.dto.event.GetAllEventDtoRes;
import com.lawencon.community.dto.event.GetByEventIdDtoDataRes;
import com.lawencon.community.dto.event.GetByEventIdDtoRes;
import com.lawencon.community.dto.event.GetCountNotPaidDtoDataRes;
import com.lawencon.community.dto.event.GetEventByCategoryDtoDataRes;
import com.lawencon.community.dto.event.GetEventByCategoryDtoRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.dto.event.InsertEventDtoDataRes;
import com.lawencon.community.dto.event.InsertEventDtoReq;
import com.lawencon.community.dto.event.InsertEventDtoRes;
import com.lawencon.community.dto.event.InvoiceEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoDataRes;
import com.lawencon.community.dto.event.UpdateEventDtoReq;
import com.lawencon.community.dto.event.UpdateEventDtoRes;
import com.lawencon.community.dto.paymentevent.UpdatePaymentEventDtoReq;
import com.lawencon.community.dto.user.EmailDtoReq;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.PaymentEvent;
import com.lawencon.community.model.PriceListEvent;
import com.lawencon.community.service.EventService;
import com.lawencon.community.service.PaymentEventService;
import com.lawencon.model.SearchQuery;

import freemarker.template.Configuration;

@Service
public class EventServiceImpl extends BaseService implements EventService {
	private static final String email = "mustajabsa@gmail.com";
	private EventDao eventDao;
	private EventTypeDao eventTypeDao;
	private CategoryDao categoryDao;
	private AttachmentDao attachmentDao;
	private PriceListEventDao priceListEventDao;
	private JavaMailSender mailSender;
	private PaymentEventDao paymentEventDao;
	private PaymentEventService paymentEventService;

	@Autowired
	public EventServiceImpl(EventDao eventDao, EventTypeDao eventTypeDao, CategoryDao categoryDao,
			AttachmentDao attachmentDao, PriceListEventDao priceListEventDao, JavaMailSender mailSender) {
		this.eventDao = eventDao;
		this.eventTypeDao = eventTypeDao;
		this.categoryDao = categoryDao;
		this.attachmentDao = attachmentDao;
		this.priceListEventDao = priceListEventDao;
		this.mailSender = mailSender;
	}
	
	@Autowired
    private Configuration freeMarkerConfiguration;
	
	@Autowired
	public void setPaymentEventDao(PaymentEventDao paymentEventDao) {
		this.paymentEventDao = paymentEventDao;
	}
	
	@Autowired
	public void setPaymentEventService(PaymentEventService paymentEventService) {
		this.paymentEventService = paymentEventService;
	}

	@Override
	public GetAllEventDtoRes findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		GetAllEventDtoRes getAll = new GetAllEventDtoRes();
		
		SearchQuery<Event> events = eventDao.findAll(query, startPage, maxPage);
		List<GetAllEventDtoDataRes> listEvent = new ArrayList<>();

		for (int i = 0; i < events.getData().size(); i++) {
			Event event = events.getData().get(i);
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(event.getId());
			data.setEventCode(event.getEventCode());
			data.setEventTitle(event.getEventTitle());
			data.setEventProvider(event.getEventProvider());
			data.setEventLocation(event.getLocation());
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

			if (event.getAttachmentId() != null) {
				data.setAttachmentId(event.getAttachmentId().getId());
				data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
			}
			
			GetByEventIdDtoDataRes eventData = eventDao.findEventStatus(event.getId());
			if(eventData != null) {
				data.setPaymentAttachment(eventData.getPaymentAttachment());
				data.setPaymentId(eventData.getPaymentId());
				data.setPaymentName(eventData.getPaymentName());
			}
			
			if(getId() != null) {				
				GetCountNotPaidDtoDataRes count = eventDao.findIsEnroll(getId());
				if(count.getCountNotPaid() > 0) {
					data.setIsEnroll(true);
				}else {
					data.setIsEnroll(false);
				}
			}else {
				data.setIsEnroll(false);
			}
			
			data.setCreatedBy(event.getCreatedBy());
			data.setVersion(event.getVersion());
			data.setIsActive(event.getIsActive());

			listEvent.add(data);
		}

		getAll.setData(listEvent);
		getAll.setMsg(null);
		getAll.setTotal(events.getCount());

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
		data.setEventLocation(event.getLocation());
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

		if (event.getAttachmentId() != null) {
			data.setAttachmentId(event.getAttachmentId().getId());
			data.setAttachmentExtension(event.getAttachmentId().getAttachmentExtension());
		}
		
		GetByEventIdDtoDataRes eventData = eventDao.findEventStatus(event.getId());
		if(eventData != null) {
			data.setPaymentAttachment(eventData.getPaymentAttachment());
			data.setPaymentId(eventData.getPaymentId());
			data.setPaymentName(eventData.getPaymentName());
		}
		
		if(getId() != null) {				
			GetCountNotPaidDtoDataRes count = eventDao.findIsEnroll(getId());
			if(count.getCountNotPaid() > 0) {
				data.setIsEnroll(true);
			}else {
				data.setIsEnroll(false);
			}
		}else {
			data.setIsEnroll(false);
		}
		
		data.setCreatedBy(event.getCreatedBy());
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
			event.setLocation(dataInsert.getEventLocation());	
			event.setEventPrice(dataInsert.getEventPrice());
			event.setEventTimeStart(dataInsert.getEventTimeStart());
			event.setEventTimeEnd(dataInsert.getEventTimeEnd());
			event.setEventDateStart(dataInsert.getEventDateStart());
			event.setEventDateEnd(dataInsert.getEventDateEnd());

			EventType eventType = eventTypeDao.findById(dataInsert.getEventTypeId());
			event.setTypeId(eventType);
			
			
			if(EventTypeConstant.COURSE.getCode().equals(eventType.getTypeCode())) {
				PriceListEvent priceListEvent = priceListEventDao.findByCode(EventTypeConstant.COURSE.getCodePrice());
				event.setPriceId(priceListEvent);
			}else if(EventTypeConstant.EVENT.getCode().equals(eventType.getTypeCode())) {
				PriceListEvent priceListEvent = priceListEventDao.findByCode(EventTypeConstant.EVENT.getCodePrice());
				event.setPriceId(priceListEvent);
			}

			Category category = categoryDao.findById(dataInsert.getCategoryId());
			event.setCategoryId(category);

			event.setCreatedBy(getId());

			if (file != null) {
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
				
				if(eventUpdate.getIsApprove() == true) {
					InvoiceEventDtoReq invoiceReq = eventDao.getDataSendInvoice(data.getId());
					UpdatePaymentEventDtoReq updateReq = new UpdatePaymentEventDtoReq();
					
					Date date = new Date();
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int month = localDate.getMonthValue();
					
					PaymentEvent paymentEvent = paymentEventDao.findById(invoiceReq.getPaymentId());
					updateReq.setId(paymentEvent.getId());
					String invoice = "IN" + month + "-" + getRandomNumericString(4);
					updateReq.setInvoice(invoice);
					updateReq.setIsApprove(true);
					updateReq.setVersion(paymentEvent.getVersion());
					updateReq.setIsActive(paymentEvent.getIsActive());
					
					paymentEventService.update(updateReq);
					
					invoiceReq.setInvoice(invoice);
					String subject = "Invoice Number " + invoiceReq.getEventTitle();
					
					EmailDtoReq emailReq = new EmailDtoReq();
					emailReq.setTo(invoiceReq.getEmail());
					emailReq.setFrom(email);
					emailReq.setSubject(subject);
					Map<String, Object> model = new HashMap<>();
			        model.put("profileName", invoiceReq.getProfileName());
			        model.put("invoice", invoice);
			        model.put("eventTitle", invoiceReq.getEventTitle());
			        model.put("eventProvider", invoiceReq.getEventProvider());
			        model.put("eventPrice", invoiceReq.getEventPrice());
			        model.put("eventDateStart", invoiceReq.getEventDateStart());
			        model.put("eventDateEnd", invoiceReq.getEventDateEnd());
			        model.put("eventTimeStart", invoiceReq.getEventTimeStart());
			        model.put("eventTimeEnd", invoiceReq.getEventTimeEnd());
			        emailReq.setModel(model);
			        
			        ExecutorService executor = Executors.newSingleThreadExecutor();

					executor.submit(() -> {
						sendEmail(emailReq);
					});
					executor.shutdown();
				}

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
		GetAllEventDtoRes dtoRes = new GetAllEventDtoRes();
		List<GetAllEventDtoDataRes> findEnrollEvent = eventDao.findEnrollEvent(id);
		
		dtoRes.setData(findEnrollEvent);
		dtoRes.setMsg(null);
		return dtoRes;
	}

	@Override
	public GetAllEventDtoRes findNotEnrollEvent() throws Exception {
		GetAllEventDtoRes getNotEnrollEvent = new GetAllEventDtoRes();

		List<Event> events = eventDao.findNotEnrollEvent(getId());
		List<GetAllEventDtoDataRes> listEvent = new ArrayList<>();

		for (int i = 0; i < events.size(); i++) {
			Event event = events.get(i);
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(event.getId());
			data.setEventCode(event.getEventCode());
			data.setEventTitle(event.getEventTitle());
			data.setEventProvider(event.getEventProvider());
			data.setEventLocation(event.getLocation());
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

			if (event.getAttachmentId() != null) {
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
	
	@Override
	public GetAllEventDtoRes findEnrollStatus(String id, boolean isApprove) throws Exception {
		GetAllEventDtoRes dtoRes = new GetAllEventDtoRes();
		List<GetAllEventDtoDataRes> findEnrollStatus = eventDao.findEnrollStatus(id, isApprove);
		
		dtoRes.setData(findEnrollStatus);
		dtoRes.setMsg(null);
		return dtoRes;
	}
	
	@Override
	public GetByEventIdDtoRes findEventStatus(String id) throws Exception {
		GetByEventIdDtoRes dtoRes = new GetByEventIdDtoRes();
		GetByEventIdDtoDataRes findEventNotApprove = eventDao.findEventStatus(id);
		
		dtoRes.setData(findEventNotApprove);
		dtoRes.setMsg(null);
		return dtoRes;
	}
	
	@Override
	public GetAllEventDtoRes findEventNotPaid(String id) throws Exception {
		GetAllEventDtoRes dtoRes = new GetAllEventDtoRes();
		List<GetAllEventDtoDataRes> findEventNotPaid = eventDao.findEventNotPaid(id);
		
		dtoRes.setData(findEventNotPaid);
		dtoRes.setMsg(null);
		return dtoRes;
	}
	
	@Override
	public GetCountNotPaidDtoDataRes countNotPaid() throws Exception {
		return eventDao.countNotPaid(getId());
	}
	
	@Override
	public GetEventByCategoryDtoRes findByCategory(String id) throws Exception {
		GetEventByCategoryDtoRes dtoRes = new GetEventByCategoryDtoRes();
		
		List<GetEventByCategoryDtoDataRes> data = null;
		if(getId() != null) {			
			data = eventDao.findByCategory(id, getId());
		}else {
			data  = eventDao.findByCategory(id);
		}
		
		dtoRes.setData(data);
		dtoRes.setMsg(null);
		
		return dtoRes;
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
