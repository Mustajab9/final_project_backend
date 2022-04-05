package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.model.Position;
import com.lawencon.model.SearchQuery;

@Repository
public class PositionDaoImpl extends BaseDao<Position> implements PositionDao {
	
	@Override
	public SearchQuery<Position> findAll(String query, Integer startPage, Integer maxPage) throws Exception {
		SearchQuery<Position> sq = new SearchQuery<>();
		List<Position> data = null;
		
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
				return super.getAll(query, startPage, maxPage, "positionName", "positionCode");
			}
		}
		
		return sq;
	}
	
	@Override
	public Position findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Position save(Position entity) throws Exception {
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
