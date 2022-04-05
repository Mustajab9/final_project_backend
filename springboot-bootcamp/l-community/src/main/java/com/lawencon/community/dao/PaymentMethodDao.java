package com.lawencon.community.dao;

import com.lawencon.community.model.PaymentMethod;
import com.lawencon.model.SearchQuery;

public interface PaymentMethodDao {
	public SearchQuery<PaymentMethod> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public PaymentMethod findById(String id) throws Exception;
	public PaymentMethod save(PaymentMethod data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
