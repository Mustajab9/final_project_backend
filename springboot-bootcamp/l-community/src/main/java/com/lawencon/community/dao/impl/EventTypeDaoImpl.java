package com.lawencon.community.dao.impl;

import java.util.ArrayList;
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
	
	@Override
	public EventType findByCode(String code) throws Exception {
		List<EventType> types = createQuery("FROM EventType WHERE typeCode = ?1", EventType.class)
											.setParameter(1, code)
											.getResultList();
		
		return resultCheck(types);	
	}
	
	@Override
	public List<?> validateDelete(String id) throws Exception {
		String sql = "SELECT e.id FORM event_types AS e WHERE e.id = ?1";
		
		List<?> listObj = createNativeQuery(sql).setParameter(1, id).setMaxResults(1).getResultList();
		List<String> result = new ArrayList<>();
		
		listObj.forEach(val -> {
			Object obj = (Object) val;
			result.add(obj != null ? obj.toString() : null);
		});
		
		return result;
	}
}
