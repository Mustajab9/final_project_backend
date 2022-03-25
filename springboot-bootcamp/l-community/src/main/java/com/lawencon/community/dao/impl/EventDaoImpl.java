package com.lawencon.community.dao.impl;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EventDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Category;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.EventType;

@Repository
public class EventDaoImpl extends BaseDao<Event> implements EventDao {
	
	@Override
	public List<Event> findAll() throws Exception {
		return super.getAll();
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
	public List<Event> findEnrollEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id, c.category_code, c.category_name, t.id, t.type_code, t.type_name,");
		builder.append(" a.id, a.attachment_extension, e.created_by, e.version, e.is_active");
		builder.append(" FORM event e");
		builder.append(" JOIN categories c ON c.id = e.category_id");
		builder.append(" JOIN event_types t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments a ON a.id = e.attachment_id");
		builder.append(" WHERE e.id IN (SELECT ed.event_id FROM enroll_detail ed WHERE ed.enroll_id IN");
		builder.append(" (SELECT ee.id FROM enroll_events ee JOIN profiles p ON p.id = ee.profile_id");
		builder.append(" WHERE p.user_id = :id))");
		builder.append(" AND e.is_approve = true");
		
		List<?> results = createNativeQuery(builder.toString())
							.setParameter("id", id)
							.getResultList();
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
			
			if(obj[16] != null) {
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
		builder.append("SELECT e.id, e.event_code, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.is_approve, c.id, c.category_code, c.category_name, t.id, t.type_code, t.type_name,");
		builder.append(" a.id, a.attachment_extension, e.created_by, e.version, e.is_active");
		builder.append(" FORM event e");
		builder.append(" JOIN categories c ON c.id = e.category_id");
		builder.append(" JOIN event_types t ON t.id = e.type_id");
		builder.append(" LEFT JOIN attachments a ON a.id = e.attachment_id");
		builder.append(" WHERE e.id NOT IN (SELECT ed.event_id FROM enroll_detail ed WHERE ed.enroll_id IN");
		builder.append(" (SELECT ee.id FROM enroll_events ee JOIN profiles p ON p.id = ee.profile_id");
		builder.append(" WHERE p.user_id = :id))");
		builder.append(" AND e.is_approve = true");

		List<?> results = createNativeQuery(builder.toString())
				.setParameter("id", id)
				.getResultList();
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
			
			if(obj[16] != null) {
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
}
