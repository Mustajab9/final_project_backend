package com.lawencon.community.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lawencon.community.dao.CategoryDao;
import com.lawencon.community.model.Category;

@Repository
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {
	
	@Override
	public List<Category> findAll() throws Exception {
		return super.getAll();
	}
	
	@Override
	public Category findById(String id) throws Exception {
		return super.getById(id);
	}
	
	@Override
	public Category save(Category entity) throws Exception {
		return super.save(entity);
	}
	
	@Override
	public boolean deleteById(String id) throws Exception {
		return super.deleteById(id);
	}
}
