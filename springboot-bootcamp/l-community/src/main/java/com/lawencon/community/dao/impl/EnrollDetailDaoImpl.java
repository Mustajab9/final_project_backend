package com.lawencon.community.dao.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.base.BaseDaoImpl;
import com.lawencon.community.dao.EnrollDetailDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.EnrollDetail;
import com.lawencon.community.model.EnrollEvent;
import com.lawencon.community.model.Event;

@Repository
public class EnrollDetailDaoImpl extends BaseDaoImpl<EnrollDetail> implements EnrollDetailDao  {

	@Override
	public List<EnrollDetail> getByEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end, ");
		builder.append("e.event_date_start, e.event_date_end, e.attachment_id, ee.id, ee.enroll_invoice, ee.is_approve, ");
		builder.append("ed.created_by, ed.\"version\", ed.is_active ");
		builder.append("FROM enroll_detail AS ed ");
		builder.append("INNER JOIN events AS e ON e.id = ed.event_id ");
		builder.append("INNER JOIN enroll_events AS ee ON ee.id = ed.enroll_id ");
		builder.append("WHERE ed.event_id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<EnrollDetail> listResult = new ArrayList<>();
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
			
			Attachment attachment = null;
			if(obj[8] != null) {
				attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[8].toString());
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				attachment.setAttachmentContent(content);
			}
			
			EnrollEvent enrollEvent = new EnrollEvent();
			enrollEvent.setId(obj[8].toString());
			enrollEvent.setEnrollInvoice(obj[9].toString());
			enrollEvent.setIsApprove(Boolean.valueOf(obj[10].toString()));
			
			EnrollDetail enrollDetail = new EnrollDetail();
			enrollDetail.setEventId(event);
			enrollDetail.setEnrollId(enrollEvent);
			enrollDetail.setCreatedBy(obj[11].toString());
			enrollDetail.setVersion(Integer.valueOf(obj[12].toString()));
			enrollDetail.setIsActive(Boolean.valueOf(obj[13].toString()));
			
			listResult.add(enrollDetail);
		});
		
		return listResult;
	}
	
	private byte[] convertObjToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        outputStream.writeObject(obj);
        outputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

}