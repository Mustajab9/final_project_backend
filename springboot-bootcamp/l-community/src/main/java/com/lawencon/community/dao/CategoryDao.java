package com.lawencon.community.dao;

import com.lawencon.community.model.Category;
import com.lawencon.model.SearchQuery;

public interface CategoryDao {
	SearchQuery<Category> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Category findById(String id) throws Exception;
	Category save(Category data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
	Category findByCode(String code) throws Exception;
}
