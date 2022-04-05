package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.model.Industry;
import com.lawencon.model.SearchQuery;

@Repository
public class IndustryDaoImpl extends BaseDao<Industry> implements IndustryDao {

	@Override
	public SearchQuery<Industry> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Industry> sq = new SearchQuery<>();
		List<Industry> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "industryName", "industryCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public Industry findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Industry save(Industry entity) throws Exception {
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
