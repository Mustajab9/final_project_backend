package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ProvinceDao;
import com.lawencon.community.model.Province;

@Repository
public class ProvinceDaoImpl extends BaseDao<Province> implements ProvinceDao {
	
	@Override
	public List<Province> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Province findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Province save(Province entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
