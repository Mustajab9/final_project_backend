package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PriceListMemberDao;
import com.lawencon.community.model.PriceListMember;
import com.lawencon.model.SearchQuery;

@Repository
public class PriceListMemberDaoImpl extends BaseDao<PriceListMember> implements PriceListMemberDao {

	@Override
	public SearchQuery<PriceListMember> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<PriceListMember> sq = new SearchQuery<>();
		List<PriceListMember> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "priceCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public PriceListMember findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public PriceListMember save(PriceListMember entity) throws Exception {
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
