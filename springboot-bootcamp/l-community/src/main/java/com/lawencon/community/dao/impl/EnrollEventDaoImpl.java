package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.EnrollEvent;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.Profiles;

@Repository
public class EnrollEventDaoImpl extends BaseDao<EnrollEvent> implements EnrollEventDao {
	
	@Override
	public List<EnrollEvent> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public EnrollEvent findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public EnrollEvent save(EnrollEvent entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<EnrollEvent> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id AS event_id, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.attachment_id AS event_attachment, p.id AS profile_id, p.profile_name,");
		builder.append(" ee.attachment_id, ee.id, ee.enroll_invoice, ee.is_approve, ee.created_by");
		builder.append(" FROM enroll_events AS ee");
		builder.append(" INNER JOIN profiles AS p ON p.id = ee.profile_id");
		builder.append(" INNER JOIN users AS u ON u.id = p.user_id");
		builder.append(" INNER JOIN events AS e ON e.id = ee.event_id");
		builder.append(" WHERE u.id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<EnrollEvent> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			Event event = new Event();
			event.setId(obj[0].toString());
			event.setEventTitle(obj[1].toString());
			event.setEventProvider(obj[2].toString());
			event.setEventPrice(BigInteger.valueOf(((Number)obj[3]).longValue()));
			event.setEventTimeStart((Time)obj[4]);
			event.setEventTimeEnd((Time)obj[5]);
			event.setEventDateStart((Date)obj[6]);
			event.setEventDateEnd((Date)obj[7]);
			
			if(obj[8] != null) {
				Attachment attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[8].toString());
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				attachment.setAttachmentContent(content);
			}
			
			Profiles profile = new Profiles();
			profile.setId(obj[9].toString());
			profile.setProfileName(obj[10].toString());
			
			EnrollEvent enrollEvent = new EnrollEvent();
			
			if(obj[11] != null) {
				Attachment attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[11].toString());
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				attachment.setAttachmentContent(content);
				enrollEvent.setAttachmentId(attachment);
			}
			
			enrollEvent.setId(obj[12].toString());
			enrollEvent.setEnrollInvoice(obj[13].toString());
			enrollEvent.setIsApprove(Boolean.valueOf(obj[14].toString()));
			enrollEvent.setProfileId(profile);
			enrollEvent.setCreatedBy(obj[15].toString());
			
			listResult.add(enrollEvent);
		});
		
		return listResult;
	}
}
