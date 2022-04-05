package com.lawencon.community.dao;

import com.lawencon.community.model.Category;
import com.lawencon.model.SearchQuery;

public interface CategoryDao {
	public SearchQuery<Category> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Category findById(String id) throws Exception;
	public Category save(Category data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
