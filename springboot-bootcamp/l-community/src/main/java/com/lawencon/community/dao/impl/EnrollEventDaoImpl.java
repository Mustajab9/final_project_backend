package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EnrollEventDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.EnrollEvent;
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
		builder.append("SELECT p.id, p.profile_name, ee.attachment_id, ee.id, ee.enroll_invoice, ee.is_approve, ee.created_by");
		builder.append(" FROM enroll_events AS ee");
		builder.append(" INNER JOIN profiles AS p ON p.id = ee.profile_id");
		builder.append(" INNER JOIN users AS u ON u.id = p.user_id");
		builder.append(" WHERE u.id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<EnrollEvent> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			Profiles profile = new Profiles();
			profile.setId(obj[0].toString());
			profile.setProfileName(obj[1].toString());
			
			EnrollEvent enrollEvent = new EnrollEvent();
			
			if(obj[2] != null) {
				Attachment attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[2].toString());
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				attachment.setAttachmentContent(content);
				enrollEvent.setAttachmentId(attachment);
			}
			
			enrollEvent.setId(obj[3].toString());
			enrollEvent.setEnrollInvoice(obj[4].toString());
			enrollEvent.setIsApprove(Boolean.valueOf(obj[5].toString()));
			enrollEvent.setProfileId(profile);
			enrollEvent.setCreatedBy(obj[6].toString());
			
			listResult.add(enrollEvent);
		});
		
		return listResult;
	}
}
