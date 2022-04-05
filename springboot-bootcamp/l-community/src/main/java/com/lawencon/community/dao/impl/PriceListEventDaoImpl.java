package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListEventDao;
import com.lawencon.community.model.PriceListEvent;
import com.lawencon.model.SearchQuery;

@Repository
public class PriceListEventDaoImpl extends BaseDao<PriceListEvent> implements PriceListEventDao {
	
	@Override
	public SearchQuery<PriceListEvent> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PriceListEvent> sq = new SearchQuery<>();
		List<PriceListEvent> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "priceName", "priceCode");
			}
		}
		
		return sq;
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
	
	@Override
	public Long countAll() {
		return super.countAll();
	}
}
