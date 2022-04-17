package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Province;
import com.lawencon.model.SearchQuery;

public interface ProvinceDao {
	SearchQuery<Province> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Province findById(String id) throws Exception;
	Province save(Province data) throws Exception;
	boolean deleteById(String id) throws Exception;
	Long countAll() throws Exception;
	Province findByCode(String code) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
