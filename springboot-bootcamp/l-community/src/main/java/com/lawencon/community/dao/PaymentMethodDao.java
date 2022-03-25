package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.PaymentMethod;

public interface PaymentMethodDao {
	public List<PaymentMethod> findAll(int startPage, int maxPage) throws Exception;
	public PaymentMethod findById(String id) throws Exception;
	public PaymentMethod save(PaymentMethod data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
