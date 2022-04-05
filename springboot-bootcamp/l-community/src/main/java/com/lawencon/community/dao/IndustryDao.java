package com.lawencon.community.dao;

import com.lawencon.community.model.Industry;
import com.lawencon.model.SearchQuery;

public interface IndustryDao {
	public SearchQuery<Industry> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Industry findById(String id) throws Exception;
	public Industry save(Industry data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
