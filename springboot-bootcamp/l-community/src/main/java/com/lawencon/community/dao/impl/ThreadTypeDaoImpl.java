package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.model.ThreadType;
import com.lawencon.model.SearchQuery;

@Repository
public class ThreadTypeDaoImpl extends BaseDao<ThreadType> implements ThreadTypeDao {
	
	@Override
	public SearchQuery<ThreadType> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<ThreadType> sq = new SearchQuery<>();
		List<ThreadType> data = null;
		
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
	public ThreadType findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadType save(ThreadType entity) throws Exception {
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
