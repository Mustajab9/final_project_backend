package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PaymentEvent;

public interface PaymentEventDao {
	public List<PaymentEvent> findAll() throws Exception;
	public PaymentEvent findById(String id) throws Exception;
	public PaymentEvent save(PaymentEvent data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<PaymentEvent> findByUser(String id) throws Exception;
}
