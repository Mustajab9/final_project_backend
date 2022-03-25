package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.model.PriceListEvent;

@Repository
public class PriceListEventDaoImpl extends BaseDao<PriceListEvent> implements PriceListEventDao {
	
	@Override
	public List<PriceListEvent> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public PriceListEvent findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PriceListEvent save(PriceListEvent entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
