package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadTypeDao;
import com.lawencon.community.model.ThreadType;

@Repository
public class ThreadTypeDaoImpl extends BaseDao<ThreadType> implements ThreadTypeDao {
	
	@Override
	public List<ThreadType> findAll() throws Exception {
		return super.getAll();
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
}
