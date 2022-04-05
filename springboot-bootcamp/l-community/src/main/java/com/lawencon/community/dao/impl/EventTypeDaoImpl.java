package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.EventTypeDao;
import com.lawencon.community.model.EventType;
import com.lawencon.model.SearchQuery;

@Repository
public class EventTypeDaoImpl extends BaseDao<EventType> implements EventTypeDao {
	
	@Override
	public SearchQuery<EventType> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<EventType> sq = new SearchQuery<>();
		List<EventType> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "typeName", "typeCode");
			}
		}
		
		return sq;
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
	
	@Override
	public Long countAll() {
		return super.countAll();
	}
}
