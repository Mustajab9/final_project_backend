package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.ThreadCategoryDao;
import com.lawencon.community.model.ThreadCategory;

@Repository
public class ThreadCategoryDaoImpl extends BaseDao<ThreadCategory> implements ThreadCategoryDao {
	
	@Override
	public List<ThreadCategory> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public ThreadCategory findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public ThreadCategory save(ThreadCategory entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
	
	@Override
	public List<ThreadCategory> findByThread(String id) throws Exception {
		return null;
	}
}
