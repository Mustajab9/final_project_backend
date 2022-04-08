package com.lawencon.community.dao.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EventDao;
import com.lawencon.community.dto.event.GetReportIncomeEventDto;
import com.lawencon.community.dto.event.GetReportProfileAttendanceEventDto;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.EventType;
import com.lawencon.community.model.User;
import com.lawencon.model.SearchQuery;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements EventDao {

//	@Override
//	public List<Event> findAll() throws Exception {
//		return super.getAll();
//	}

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
				return super.getAll(query, startPage, maxPage, "eventTitle");
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
	public List<Event> findEnrollEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(
				" e.event_date_start, e.event_date_end, e.is_approve, c.id, c.category_code, c.category_name, t.id, t.type_code, t.type_name,");
		builder.append(" a.id, a.attachment_extension, e.created_by, e.version, e.is_active");
		builder.append(" FORM event e");
		builder.append(" JOIN categories c ON c.id = e.category_id");
		builder.append(" JOIN event_types t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments a ON a.id = e.attachment_id");
		builder.append(
				" WHERE e.id IN (SELECT ee.id FROM enroll_events ee JOIN profiles p ON p.id = ee.profile_id WHERE p.user_id = :id)");
		builder.append(" AND e.is_approve = true");

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

			listResult.add(data);
		});

		return listResult;
	}

	@Override
	public List<Event> findNotEnrollEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append(
				"SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(
				" e.event_date_start, e.event_date_end, e.is_approve, c.id, c.category_code, c.category_name, t.id, t.type_code, t.type_name,");
		builder.append(" a.id, a.attachment_extension, e.created_by, e.version, e.is_active");
		builder.append(" FORM event e");
		builder.append(" JOIN categories c ON c.id = e.category_id");
		builder.append(" JOIN event_types t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments a ON a.id = e.attachment_id");
		builder.append(
				" WHERE e.id NOT IN (SELECT ee.id FROM enroll_events ee JOIN profiles p ON p.id = ee.profile_id WHERE p.user_id = :id)");
		builder.append(" AND e.is_approve = true");

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
		builder.append(
				"SELECT e.event_price, COUNT(ped.created_by), e.event_title, e.event_provider, e.event_date_start, e.event_date_end, e.\"location\"");
		builder.append(" FROM events e");
		builder.append(" JOIN payment_event_detail ped ON ped.event_id = e.id");
		builder.append(
				" WHERE ped.payment_event_id IN (SELECT pe.id FROM payment_events pe WHERE pe.is_approve = true)");
		builder.append(" AND e.id = :id");
		builder.append(
				" GROUP BY e.event_price, e.event_title, e.event_provider, e.event_date_start, e.event_date_end, e.\"location\"");
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

	

}
