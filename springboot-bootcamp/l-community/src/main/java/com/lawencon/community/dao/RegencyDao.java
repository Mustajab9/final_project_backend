package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Regency;
import com.lawencon.model.SearchQuery;

public interface RegencyDao {
	SearchQuery<Regency> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	Regency findById(String id) throws Exception;
	Regency save(Regency data) throws Exception;
	boolean deleteById(String id) throws Exception;
	List<Regency> findByProvince(String id) throws Exception;
	Long countAll() throws Exception;
	Regency findByCode(String code) throws Exception;
	List<?> validateDelete(String id) throws Exception;
}
