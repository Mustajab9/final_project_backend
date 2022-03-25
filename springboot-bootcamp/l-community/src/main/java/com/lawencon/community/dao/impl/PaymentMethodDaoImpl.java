package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.model.PaymentMethod;

@Repository
public class PaymentMethodDaoImpl extends BaseDao<PaymentMethod> implements PaymentMethodDao {
	
	@Override
	public List<PaymentMethod> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public PaymentMethod findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PaymentMethod save(PaymentMethod entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
