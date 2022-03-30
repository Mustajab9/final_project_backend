package com.lawencon.community.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PaymentEventDao;
import com.lawencon.community.model.Attachment;
import com.lawencon.community.model.PaymentEvent;

@Repository
public class PaymentEventDaoImpl extends BaseDao<PaymentEvent> implements PaymentEventDao {
	
	@Override
	public List<PaymentEvent> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public PaymentEvent findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PaymentEvent save(PaymentEvent entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<PaymentEvent> findByUser(String id) throws Exception {
		StringBuilder builder = new StringBuilder();
		builder.append("SELECT pe.attachment_id, pe.id, pe.payment_events_invoice, pe.is_approve, pe.created_by");
		builder.append(" FROM payment_events AS pe");
		builder.append(" WHERE pe.created_by = :id");
		
		List<?> results = createNativeQuery(builder.toString()).getResultList();
		List<PaymentEvent> listResult = new ArrayList<>();
		
		results.forEach(result -> {
			Object[] obj = (Object[]) result;
			
			PaymentEvent payemntEvent = new PaymentEvent();
			
			if(obj[2] != null) {
				Attachment attachment = new Attachment();
				byte[] content = null;
				try {
					content = convertObjToByteArray(obj[2].toString());
				}catch (IOException e) {
					e.printStackTrace();
				}
				
				attachment.setAttachmentContent(content);
				payemntEvent.setAttachmentId(attachment);
			}
			
			payemntEvent.setId(obj[3].toString());
			payemntEvent.setPaymentEventInvoice(obj[4].toString());
			payemntEvent.setIsApprove(Boolean.valueOf(obj[5].toString()));
			payemntEvent.setCreatedBy(obj[6].toString());
			
			listResult.add(payemntEvent);
		});
		
		return listResult;
	}
}
