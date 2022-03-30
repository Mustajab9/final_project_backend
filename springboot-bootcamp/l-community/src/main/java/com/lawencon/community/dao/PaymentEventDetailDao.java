package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PaymentEventDetail;

public interface PaymentEventDetailDao {
	public List<PaymentEventDetail> findAll() throws Exception;
	public PaymentEventDetail findById(String id) throws Exception;
	public PaymentEventDetail save(PaymentEventDetail data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<PaymentEventDetail> findByEvent(String id) throws Exception;
}
