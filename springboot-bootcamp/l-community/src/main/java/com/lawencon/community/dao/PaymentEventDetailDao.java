package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PaymentEventDetail;

public interface PaymentEventDetailDao {
	List<PaymentEventDetail> findAll() throws Exception;
	PaymentEventDetail findById(String id) throws Exception;
	PaymentEventDetail save(PaymentEventDetail data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<PaymentEventDetail> findByEvent(String id) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
