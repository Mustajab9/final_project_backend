package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.model.EventType;

@Repository
public class EventTypeDaoImpl extends BaseDao<EventType> implements EventTypeDao {
	
	@Override
	public List<EventType> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
	}
	
	@Override
	public EventType findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public EventType save(EventType entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
