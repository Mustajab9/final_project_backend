package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PaymentEvent;

public interface PaymentEventDao {
	List<PaymentEvent> findAll() throws Exception;
	PaymentEvent findById(String id) throws Exception;
	PaymentEvent save(PaymentEvent data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<PaymentEvent> findByUser(String id) throws Exception;
}
