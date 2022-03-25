package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.PositionDao;
import com.lawencon.community.model.Position;

@Repository
public class PositionDaoImpl extends BaseDao<Position> implements PositionDao {
	
	@Override
	public List<Position> findAll(int startPage, int maxPage) throws Exception {
		return super.getAll(startPage, maxPage);
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
}
