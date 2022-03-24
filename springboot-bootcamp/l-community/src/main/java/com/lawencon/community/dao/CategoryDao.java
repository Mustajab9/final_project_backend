package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Category;

public interface CategoryDao {
	public List<Category> findAll() throws Exception;
	public Category findById(String id) throws Exception;
	public Category save(Category data) throws Exception;
	public boolean deleteById(String id) throws Exception;
}
