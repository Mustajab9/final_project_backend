package com.lawencon.community.dao;

import java.util.List;

import com.lawencon.community.model.Regency;
import com.lawencon.model.SearchQuery;

public interface RegencyDao {
	public SearchQuery<Regency> findAll(String query, Integer startPage, Integer maxPage) throws Exception;
	public Regency findById(String id) throws Exception;
	public Regency save(Regency data) throws Exception;
	public boolean deleteById(String id) throws Exception;
	public List<Regency> findByProvince(String code) throws Exception;
	public Long countAll() throws Exception;
}
