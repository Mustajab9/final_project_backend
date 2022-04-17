package com.lawencon.community.dao;

import com.lawencon.community.model.PaymentMethod;
import com.lawencon.model.SearchQuery;

public interface PaymentMethodDao {
	SearchQuery<PaymentMethod> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	PaymentMethod findById(String id) throws Exception;
	PaymentMethod save(PaymentMethod data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
	PaymentMethod findByCode(String code) throws Exception;
}
