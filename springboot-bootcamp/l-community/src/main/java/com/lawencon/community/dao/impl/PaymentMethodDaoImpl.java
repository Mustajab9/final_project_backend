package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PaymentMethodDao;
import com.lawencon.community.model.PaymentMethod;
import com.lawencon.model.SearchQuery;

@Repository
public class PaymentMethodDaoImpl extends BaseDao<PaymentMethod> implements PaymentMethodDao {
	
	@Override
	public SearchQuery<PaymentMethod> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PaymentMethod> sq = new SearchQuery<>();
		List<PaymentMethod> data = null;
		
		if(startPage == null || maxPage == null) {
			data = getAll();
			sq.setData(data);
		}else {
			if(query == null) {
				data = getAll(startPage, maxPage);
				int count = countAll().intValue();
				
				sq.setData(data);
				sq.setCount(count);
			}else {
				return super.getAll(query, startPage, maxPage, "paymentName", "paymentCode");
			}
		}
		
		return sq;
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
	
	@Override
	public Long countAll() {
		return super.countAll();
	}
}
