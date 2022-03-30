package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PaymentEventDetailDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.Event;
import com.lawencon.community.model.PaymentEvent;
import com.lawencon.community.model.PaymentEventDetail;

@Repository
public class PaymentEventDetailDaoImpl extends BaseDao<PaymentEventDetail> implements PaymentEventDetailDao  {

	@Override
	public List<PaymentEventDetail> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public PaymentEventDetail findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PaymentEventDetail save(PaymentEventDetail entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<PaymentEventDetail> findByEvent(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT e.id, e.event_title, e.event_provider, e.event_price, e.event_time_start, e.event_time_end,");
		builder.append(" e.event_date_start, e.event_date_end, e.attachment_id, pe.id AS payment_events_id, pe.payment_events_invoice, pe.is_approve,");
		builder.append(" ed.created_by, ed.version, ed.is_active");
		builder.append(" FROM payment_event_detail AS ed");
		builder.append(" INNER JOIN events AS e ON e.id = ed.event_id");
		builder.append(" INNER JOIN payment_events AS pe ON pe.id = ed.payment_event_id");
		builder.append(" WHERE ed.event_id = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<PaymentEventDetail> listResult = new ArrayList<>();
		
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
			
			PaymentEvent paymentEvent = new PaymentEvent();
			paymentEvent.setId(obj[8].toString());
			paymentEvent.setPaymentEventInvoice(obj[9].toString());
			paymentEvent.setIsApprove(Boolean.valueOf(obj[10].toString()));
			
			PaymentEventDetail enrollDetail = new PaymentEventDetail();
			enrollDetail.setEventId(event);
			enrollDetail.setPaymentId(paymentEvent);
			enrollDetail.setCreatedBy(obj[11].toString());
			enrollDetail.setVersion(Integer.valueOf(obj[12].toString()));
			enrollDetail.setIsActive(Boolean.valueOf(obj[13].toString()));
			
			listResult.add(enrollDetail);
		});
		
		return listResult;
	}
}