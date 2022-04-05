package com.lawencon.community.dao;

import com.lawencon.community.model.Province;
import com.lawencon.model.SearchQuery;

public interface ProvinceDao {
	public SearchQuery<Province> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Province findById(String id) throws Exception;
	public Province save(Province data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public Long countAll() throws Exception;
}
