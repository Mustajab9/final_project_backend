package com.lawencon.community.dao;

import com.lawencon.community.model.Industry;
import com.lawencon.model.SearchQuery;

public interface IndustryDao {
	SearchQuery<Industry> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Industry findById(String id) throws Exception;
	Industry save(Industry data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
}
