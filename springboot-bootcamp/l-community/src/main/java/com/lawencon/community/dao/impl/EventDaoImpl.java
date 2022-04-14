package com.lawencon.community.dao.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dto.event.GetAllEventDtoDataRes;
import com.lawencon.community.dto.event.GetByEventIdDtoDataRes;
import com.lawencon.community.dto.event.GetCountNotPaidDtoDataRes;
import com.lawencon.community.dto.event.GetEventByCategoryDtoDataRes;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.dto.event.InvoiceEventDtoReq;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements EventDao {

	@Override
	public SearchQuery<Event> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Event> sq = new SearchQuery<>();
		List<Event> data = null;
		
		if(startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		}else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			}else {
				return super.getAll(query, startPage, maxPage, "eventTitle", "eventProvider", "location", "typeId.typeName", "categoryId.categoryName");
			}
		}
		
		return sq;
	}

	@Override
	public Event findById(String id) throws Exception {
		return super.getById(id);
	}

	@Override
	public Event save(Event entity) throws Exception {
		return super.save(entity);
	}

	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public Long countAll() {
		return super.countAll();
	}

	@Override
	public List<GetAllEventDtoDataRes> findEnrollEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id AS category_id, c.category_name, t.id AS type_id, t.type_name,");
		builder.append(" a.id AS attachment_id, a.attachment_extension, e.created_by, e.version, e.is_active, e.location, ee.is_approve AS enroll_approve, ee.enroll_invoice");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" LEFT JOIN enroll_events AS ee ON e.id = ee.event_id");
		builder.append(" WHERE e.id IN (SELECT ee.event_id FROM enroll_events AS ee WHERE ee.created_by = :id)");
		builder.append(" AND e.is_approve = true");
		builder.append(" ORDER BY e.event_date_start ASC");

		List<?> results = createNativeQuery(builder.toString()).setParameter("id", id).getResultList();
		List<GetAllEventDtoDataRes> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventTimeStart((Time) obj[5]);
			data.setEventTimeEnd((Time) obj[6]);
			data.setEventDateStart((Date) obj[7]);
			data.setEventDateEnd((Date) obj[8]);
			data.setIsApprove(Boolean.valueOf(obj[9].toString()));
			data.setCategoryId(obj[10].toString());
			data.setCategoryName(obj[11].toString());
			data.setTypeId(obj[12].toString());
			data.setTypeName(obj[13].toString());

			if (obj[14] != null) {
				data.setAttachmentId(obj[14].toString());
				data.setAttachmentExtension(obj[15].toString());
			}

			data.setCreatedBy(obj[16].toString());
			data.setVersion(Integer.valueOf(obj[17].toString()));
			data.setIsActive(Boolean.valueOf(obj[18].toString()));
			data.setEventLocation(obj[19].toString());
			data.setEnrollIsApprove(Boolean.valueOf(obj[20].toString()));
			data.setEnrollInvoice(obj[21].toString());
			
			listResult.add(data);
		});

		return listResult;
	}

	@Override
	public List<Event> findNotEnrollEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id AS category_id, c.category_code, c.category_name, t.id AS type_id, t.type_code, t.type_name,");
		builder.append(" a.id AS attachment_id, a.attachment_extension, e.created_by, e.version, e.is_active, e.location");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" WHERE e.id NOT IN (SELECT ee.event_id FROM enroll_events AS ee WHERE ee.created_by = :id)");
		builder.append(" AND e.created_by != :id AND e.is_approve = true");

		List<?> results = createNativeQuery(builder.toString()).setParameter("id", id).getResultList();
		List<Event> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			Event data = new Event();

			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventTimeStart((Time) obj[5]);
			data.setEventTimeEnd((Time) obj[6]);
			data.setEventDateStart((Date) obj[7]);
			data.setEventDateEnd((Date) obj[8]);
			data.setIsApprove(Boolean.valueOf(obj[9].toString()));

			Category category = new Category();
			category.setId(obj[10].toString());
			category.setCategoryCode(obj[11].toString());
			category.setCategoryName(obj[12].toString());
			data.setCategoryId(category);

			EventType type = new EventType();
			type.setId(obj[13].toString());
			type.setTypeCode(obj[14].toString());
			type.setTypeName(obj[15].toString());
			data.setTypeId(type);

			if (obj[16] != null) {
				Attachment attachment = new Attachment();
				attachment.setId(obj[16].toString());
				attachment.setAttachmentExtension(obj[17].toString());
				data.setAttachmentId(attachment);
			}

			data.setCreatedBy(obj[18].toString());
			data.setVersion(Integer.valueOf(obj[19].toString()));
			data.setIsActive(Boolean.valueOf(obj[20].toString()));
			data.setLocation(obj[21].toString());

			listResult.add(data);
		});

		return listResult;
	}

	@Override
	public List<GetReportProfileAttendanceEventDto> getReportEnrolls(String eventId) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.event_title AS eventTitle, e.event_provider AS eventProvider,");
		builder.append(" e.event_price AS eventPrice, e.event_date_start AS eventDateStart,");
		builder.append(" e.event_date_end AS eventDateEnd, e.event_time_start AS eventTimeStart,");
		builder.append(" e.event_time_end AS eventTimeEnd, p.profile_name AS profileName,");
		builder.append(" p.profile_company AS profileCompany, p.profile_phone AS profilePhone,");
		builder.append(" u.email AS email, r.regency_name AS regencyName,");
		builder.append(" pr.province_name AS provinceName");
		builder.append(" FROM profiles p");
		builder.append(" INNER JOIN users u ON u.id = p.user_id");
		builder.append(" INNER JOIN regencies r ON r.id = p.regency_id");
		builder.append(" INNER JOIN provinces pr ON pr.id = r.province_id");
		builder.append(" INNER JOIN events e ON e.id = :eventId");
		builder.append(" WHERE p.id IN (SELECT profile_id FROM enroll_events WHERE enroll_events.event_id = :eventId)");
		
		List<?> results = createNativeQuery(builder.toString()).setParameter("eventId", eventId).getResultList();
		List<GetReportProfileAttendanceEventDto> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetReportProfileAttendanceEventDto data = new GetReportProfileAttendanceEventDto();

			data.setEventTitle(obj[0].toString());
			data.setEventProvider(obj[1].toString());
			data.setEventPrice(obj[2].toString());
			data.setEventDateStart(obj[3].toString());
			data.setEventDateEnd(obj[4].toString());
			data.setEventTimeStart(obj[5].toString());
			data.setEventTimeEnd(obj[6].toString());
			data.setProfileName(obj[7].toString());
			data.setProfileCompany(obj[8].toString());
			data.setProfilePhone(obj[9].toString());
			data.setEmail(obj[10].toString());
			data.setRegencyName(obj[11].toString());
			data.setProvinceName(obj[12].toString());

			listResult.add(data);
		});

		return listResult;
	}

	@Override
	public List<GetReportIncomeEventDto> getReportIncome(String eventId) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.event_price, COUNT(ped.created_by), e.event_title, e.event_provider, e.event_date_start, e.event_date_end, e.\"location\"");
		builder.append(" FROM events e");
		builder.append(" INNER JOIN payment_event_detail ped ON ped.event_id = e.id");
		builder.append(" WHERE ped.payment_event_id IN (SELECT pe.id FROM payment_events pe WHERE pe.is_approve = true)");
		builder.append(" AND e.id = :id");
		builder.append(" GROUP BY e.event_price, e.event_title, e.event_provider, e.event_date_start, e.event_date_end, e.\"location\"");
		
		List<?> results = createNativeQuery(builder.toString()).setParameter("id", eventId).getResultList();
		List<GetReportIncomeEventDto> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetReportIncomeEventDto data = new GetReportIncomeEventDto();

			data.setEventPrice(obj[0].toString());
			data.setTotalPeople(Long.valueOf(obj[1].toString()));
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventDateStart(obj[4].toString());
			data.setEventDateEnd(obj[5].toString());
			data.setLocation(obj[6].toString());

			listResult.add(data);
		});

		return listResult;
	}
	
	@Override
	public List<GetAllEventDtoDataRes> findEnrollStatus(String id, boolean isApprove) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.location,");
		builder.append(" e.event_time_start, e.event_time_end, e.event_date_start, e.event_date_end, e.is_approve,");
		builder.append(" c.id AS category_id, c.category_name, t.id AS type_id, t.type_name, a.id AS attachment_id, a.attachment_extension,");
		builder.append(" ee.attachment_id AS payment_attachment, pm.id AS payment_id, pm.payment_name, e.version, e.is_active");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" INNER JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" INNER JOIN enroll_events AS ee ON e.id = ee.event_id");
		builder.append(" INNER JOIN attachments AS attac ON attac.id = ee.attachment_id");
		builder.append(" INNER JOIN payment_method AS pm ON pm.id = ee.payment_id");
		builder.append(" WHERE ee.is_approve = :isApprove");
		builder.append(" AND e.created_by = :id");

		List<?> results = createNativeQuery(builder.toString())
							.setParameter("isApprove", isApprove)
							.setParameter("id", id)
							.getResultList();
		List<GetAllEventDtoDataRes> listResult = new ArrayList<>();

		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();

			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventLocation(obj[5].toString());
			data.setEventTimeStart((Time) obj[6]);
			data.setEventTimeEnd((Time) obj[7]);
			data.setEventDateStart((Date) obj[8]);
			data.setEventDateEnd((Date) obj[9]);
			data.setIsApprove(Boolean.valueOf(obj[10].toString()));
			data.setCategoryId(obj[11].toString());
			data.setCategoryName(obj[12].toString());
			data.setTypeId(obj[13].toString());
			data.setTypeName(obj[14].toString());

			if (obj[15] != null) {
				data.setAttachmentId(obj[15].toString());
				data.setAttachmentExtension(obj[16].toString());
			}
			
			if(obj[17] != null) {
				data.setPaymentAttachment(obj[17].toString());
				data.setPaymentId(obj[18].toString());
				data.setPaymentName(obj[19].toString());
			}

			data.setVersion(Integer.valueOf(obj[20].toString()));
			data.setIsActive(Boolean.valueOf(obj[21].toString()));

			listResult.add(data);
		});

		return listResult;
	}
	
	@Override
	public GetByEventIdDtoDataRes findEventStatus(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.location,");
		builder.append(" e.event_time_start, e.event_time_end, e.event_date_start, e.event_date_end, e.is_approve,");
		builder.append(" c.id AS category_id, c.category_name, t.id AS type_id, t.type_name, a.id AS attachment_id, a.attachment_extension,");
		builder.append(" pe.attachment_id AS payment_attachment, pm.id AS payment_id, pm.payment_name, e.version, e.is_active");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" INNER JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" INNER JOIN payment_event_detail AS ped ON e.id = ped.event_id");
		builder.append(" INNER JOIN payment_events AS pe ON pe.id = ped.payment_event_id");
		builder.append(" INNER JOIN attachments AS attac ON attac.id = pe.attachment_id");
		builder.append(" INNER JOIN payment_method AS pm ON pm.id = pe.payment_id");
		builder.append(" WHERE e.id = :id");

		
		GetByEventIdDtoDataRes data = null;
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			
			Object[] obj = (Object[]) result;
			
			data = new GetByEventIdDtoDataRes();
			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventLocation(obj[5].toString());
			data.setEventTimeStart((Time) obj[6]);
			data.setEventTimeEnd((Time) obj[7]);
			data.setEventDateStart((Date) obj[8]);
			data.setEventDateEnd((Date) obj[9]);
			data.setIsApprove(Boolean.valueOf(obj[10].toString()));
			data.setCategoryId(obj[11].toString());
			data.setCategoryName(obj[12].toString());
			data.setTypeId(obj[13].toString());
			data.setTypeName(obj[14].toString());
			
			if (obj[15] != null) {
				data.setAttachmentId(obj[15].toString());
				data.setAttachmentExtension(obj[16].toString());
			}
			
			if(obj[17] != null) {
				data.setPaymentAttachment(obj[17].toString());
				data.setPaymentId(obj[18].toString());
				data.setPaymentName(obj[19].toString());
			}
			
			data.setVersion(Integer.valueOf(obj[20].toString()));
			data.setIsActive(Boolean.valueOf(obj[21].toString()));
		
		}catch(NoResultException | NonUniqueResultException e) {
			e.printStackTrace();
		}

		return data;
	}
	
	@Override
	public List<GetAllEventDtoDataRes> findEventNotPaid(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.location,");
		builder.append(" e.event_time_start, e.event_time_end, e.event_date_start, e.event_date_end, e.is_approve,");
		builder.append(" c.id AS category_id, c.category_name, t.id AS type_id, t.type_name, a.id AS attachment_id, a.attachment_extension,");
		builder.append(" pe.attachment_id AS payment_attachment, pm.id AS payment_id, pm.payment_name, e.version, e.is_active");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" INNER JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" LEFT JOIN payment_event_detail AS ped ON e.id = ped.event_id");
		builder.append(" LEFT JOIN payment_events AS pe ON pe.id = ped.payment_event_id");
		builder.append(" LEFT JOIN attachments AS attac ON attac.id = pe.attachment_id");
		builder.append(" LEFT JOIN payment_method AS pm ON pm.id = pe.payment_id");
		builder.append(" WHERE e.id NOT IN (SELECT ped.event_id FROM payment_event_detail ped WHERE e.created_by = :id)");
		builder.append(" AND e.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("id", id)
							.getResultList();
		List<GetAllEventDtoDataRes> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetAllEventDtoDataRes data = new GetAllEventDtoDataRes();
			
			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventLocation(obj[5].toString());
			data.setEventTimeStart((Time) obj[6]);
			data.setEventTimeEnd((Time) obj[7]);
			data.setEventDateStart((Date) obj[8]);
			data.setEventDateEnd((Date) obj[9]);
			data.setIsApprove(Boolean.valueOf(obj[10].toString()));
			data.setCategoryId(obj[11].toString());
			data.setCategoryName(obj[12].toString());
			data.setTypeId(obj[13].toString());
			data.setTypeName(obj[14].toString());
			
			if (obj[15] != null) {
				data.setAttachmentId(obj[15].toString());
				data.setAttachmentExtension(obj[16].toString());
			}
			
			if(obj[17] != null) {
				data.setPaymentAttachment(obj[17].toString());
				data.setPaymentId(obj[18].toString());
				data.setPaymentName(obj[19].toString());
			}
			
			data.setVersion(Integer.valueOf(obj[20].toString()));
			data.setIsActive(Boolean.valueOf(obj[21].toString()));
			
			listResult.add(data);
		});
		
		return listResult;
	}
	
	@Override
	public GetCountNotPaidDtoDataRes countNotPaid(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(e.id)");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" INNER JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" LEFT JOIN payment_event_detail AS ped ON e.id = ped.event_id");
		builder.append(" LEFT JOIN payment_events AS pe ON pe.id = ped.payment_event_id");
		builder.append(" LEFT JOIN attachments AS attac ON attac.id = pe.attachment_id");
		builder.append(" LEFT JOIN payment_method AS pm ON pm.id = pe.payment_id");
		builder.append(" WHERE e.id NOT IN (SELECT ped.event_id FROM payment_event_detail ped WHERE e.created_by = :id)");
		builder.append(" AND e.created_by = :id");
		
		GetCountNotPaidDtoDataRes countNotPaid = new GetCountNotPaidDtoDataRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			countNotPaid.setCountNotPaid(Integer.valueOf(result.toString()));
			
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return countNotPaid;
	}
	
	@Override
	public GetCountNotPaidDtoDataRes findIsEnroll(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT COUNT(e.id)");
		builder.append(" FROM events AS e");
		builder.append(" WHERE e.id IN");
		builder.append(" (SELECT ee.event_id FROM enroll_events ee");
		builder.append(" INNER JOIN profiles p ON p.id = ee.profile_id");
		builder.append(" INNER JOIN users u ON u.id = p.user_id WHERE u.id =:id)");
		
		GetCountNotPaidDtoDataRes countNotPaid = new GetCountNotPaidDtoDataRes();
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			countNotPaid.setCountNotPaid(Integer.valueOf(result.toString()));
			
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return countNotPaid;
	}
	
	@Override
	public InvoiceEventDtoReq getDataSendInvoice(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT p.profile_name, pe.payment_events_invoice, e.event_provider, e.event_price,");
		builder.append(" e.event_date_start, e.event_date_end, e.event_time_start, e.event_time_end, pe.id, u.email, e.event_title");
		builder.append(" FROM events AS e");
		builder.append(" LEFT JOIN payment_event_detail AS ped ON e.id = ped.event_id");
		builder.append(" LEFT JOIN payment_events AS pe ON pe.id = ped.payment_event_id");
		builder.append(" INNER JOIN profiles AS p ON p.user_id = ped.created_by");
		builder.append(" INNER JOIN users AS u ON u.id = p.user_id");
		builder.append(" WHERE e.id = :id");
		
		InvoiceEventDtoReq data = null;
		try {
			Object result = createNativeQuery(builder.toString())
					.setParameter("id", id)
					.getSingleResult();
			
			
			Object[] obj = (Object[]) result;
			
			data = new InvoiceEventDtoReq();
			data.setProfileName(obj[0].toString());
			
			if(obj[1] != null) {				
				data.setInvoice(obj[1].toString());
			}
			
			data.setEventProvider(obj[2].toString());
			data.setEventPrice(obj[3].toString());
			data.setEventDateStart(obj[4].toString());
			data.setEventDateEnd(obj[5].toString());
			data.setEventTimeStart(obj[6].toString());
			data.setEventTimeEnd(obj[7].toString());
			
			if(obj[8] != null) {				
				data.setPaymentId(obj[8].toString());
			}
			
			data.setEmail(obj[9].toString());
			data.setEventTitle(obj[10].toString());
			
		}catch (NoResultException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	@Override
	public List<GetEventByCategoryDtoDataRes> findByCategory(String id, String userId) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id AS category_id, c.category_name, t.id AS type_id, t.type_name,");
		builder.append(" a.id AS attachment_id, a.attachment_extension, e.created_by, e.version, e.is_active, e.location");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" WHERE c.id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<GetEventByCategoryDtoDataRes> resultList = new ArrayList<>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetEventByCategoryDtoDataRes data = new GetEventByCategoryDtoDataRes();
			
			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventTimeStart((Time) obj[5]);
			data.setEventTimeEnd((Time) obj[6]);
			data.setEventDateStart((Date) obj[7]);
			data.setEventDateEnd((Date) obj[8]);
			data.setIsApprove(Boolean.valueOf(obj[9].toString()));
			data.setCategoryId(obj[10].toString());
			data.setCategoryName(obj[11].toString());
			data.setTypeId(obj[12].toString());
			data.setTypeName(obj[13].toString());

			if (obj[14] != null) {
				data.setAttachmentId(obj[14].toString());
				data.setAttachmentExtension(obj[15].toString());
			}
					
			try {
				GetCountNotPaidDtoDataRes count = findIsEnroll(userId);
				if(count.getCountNotPaid() > 0) {
					data.setIsEnroll(true);
				}else {
					data.setIsEnroll(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			

			data.setCreatedBy(obj[16].toString());
			data.setVersion(Integer.valueOf(obj[17].toString()));
			data.setIsActive(Boolean.valueOf(obj[18].toString()));
			data.setEventLocation(obj[19].toString());
			
			resultList.add(data);
		});
		
		return resultList;
	}
	
	@Override
	public List<GetEventByCategoryDtoDataRes> findByCategory(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id AS category_id, c.category_name, t.id AS type_id, t.type_name,");
		builder.append(" a.id AS attachment_id, a.attachment_extension, e.created_by, e.version, e.is_active, e.location");
		builder.append(" FROM events AS e");
		builder.append(" INNER JOIN categories AS c ON c.id = e.category_id");
		builder.append(" INNER JOIN event_types AS t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments AS a ON a.id = e.attachment_id");
		builder.append(" WHERE c.id = :id");
		
		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
		
		List<GetEventByCategoryDtoDataRes> resultList = new ArrayList<>();
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			GetEventByCategoryDtoDataRes data = new GetEventByCategoryDtoDataRes();
			
			data.setId(obj[0].toString());
			data.setEventCode(obj[1].toString());
			data.setEventTitle(obj[2].toString());
			data.setEventProvider(obj[3].toString());
			data.setEventPrice(BigInteger.valueOf(((Number) obj[4]).longValue()));
			data.setEventTimeStart((Time) obj[5]);
			data.setEventTimeEnd((Time) obj[6]);
			data.setEventDateStart((Date) obj[7]);
			data.setEventDateEnd((Date) obj[8]);
			data.setIsApprove(Boolean.valueOf(obj[9].toString()));
			data.setCategoryId(obj[10].toString());
			data.setCategoryName(obj[11].toString());
			data.setTypeId(obj[12].toString());
			data.setTypeName(obj[13].toString());

			if (obj[14] != null) {
				data.setAttachmentId(obj[14].toString());
				data.setAttachmentExtension(obj[15].toString());
			}
						
			data.setIsEnroll(false);
			data.setCreatedBy(obj[16].toString());
			data.setVersion(Integer.valueOf(obj[17].toString()));
			data.setIsActive(Boolean.valueOf(obj[18].toString()));
			data.setEventLocation(obj[19].toString());
			
			resultList.add(data);
		});
		
		return resultList;
	}
}
