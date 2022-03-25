package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.IndustryDao;
import com.lawencon.community.model.Industry;

@Repository
public class IndustryDaoImpl extends BaseDao<Industry> implements IndustryDao {

	@Override
	public List<Industry> findAll() throws Exception {
		return super.getAll();
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
}
